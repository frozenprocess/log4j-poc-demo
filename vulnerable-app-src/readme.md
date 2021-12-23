# Vulnerable app
This code is vulnerable to [CVE-2021-44228](https://nvd.nist.gov/vuln/detail/CVE-2021-44228) which can result in remote code execution.
Do not use this on any server exposed to untrsuted networks.

# How to build
```
docker build -t vulnerable-app .
```