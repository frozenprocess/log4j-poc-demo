A simple docker container to run  metasploit.

# How to build
```
docker build -t log4j-scanner .
```

# Environment variables

These are adjustable via environment variables.
```
ENV SERVER_PORT=389
ENV LDAP_TIMEOUT=2
ENV THREADS=12
```