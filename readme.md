```
git clone https://github.com/frozenprocess/log4j-poc-demo.git
```

First create a Kubernetes cluster
```
kind create cluster --config cluster.yml
```

Install `tigera-operator`
```
kubectl create -f https://docs.projectcalico.org/manifests/tigera-operator.yaml
```

Create custom resources
```
kubectl create -f https://docs.projectcalico.org/manifests/custom-resources.yaml
---

**Note**: Do not apply this manifest in a production environment.
Install vulnerable application
---
kubectl apply -f https://raw.githubusercontent.com/frozenprocess/log4j-poc-demo/main/vulnerable-demo.yml
---

Checkout content of the `tmp` folder in the vulnerable pod
---
kubectl exec -it  deployments/vuln-pod -n log4j-demo -- ls /tmp
---

Run the exploit server in the background
---
docker run -d --name log4j-poc-exploit --net kind rezareza/jndi-app
---

Execute the expolit to attack the Pod
---
docker exec log4j-poc-exploit /bin/sh -c /jndi/expolit.sh
---

Check out the content of `tmp` folder again you should see a new file
---
kubectl exec -it  deployments/vuln-pod -n log4j-demo -- ls /tmp
---

Force a pod recreation by removing the pod
---
kubectl delete pod -l app=vuln-pod-services -n log4j-demo
---

Apply the simply policy to network connection for the `vulnerable-pod`.
---
kubectl apply -f https://raw.githubusercontent.com/frozenprocess/log4j-poc-demo/main/policy.yml
---

Execute the expolit again
---
docker exec log4j-poc-exploit /bin/sh -c /jndi/expolit.sh
---

Check out the content of `tmp` folder, there should not be any new files.
---
kubectl exec -it  deployments/vuln-pod -n log4j-demo -- ls /tmp
---

# Cleanup

---
kind delete cluster --name log4j-poc
docker rm -f $(docker ps --filter="name=log4j-poc-exploit"  -qa)
---