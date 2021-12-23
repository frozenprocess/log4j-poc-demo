FROM ubuntu:latest

# update repos
RUN apt update && apt install curl gnupg -y

# install latest metasploit
RUN curl https://raw.githubusercontent.com/rapid7/metasploit-omnibus/master/config/templates/metasploit-framework-wrappers/msfupdate.erb > msfinstall && \
  chmod 755 msfinstall && \
  ./msfinstall

# change to workdir
WORKDIR /app
COPY entry.sh /app/

ENV SERVER_PORT=389
ENV LDAP_TIMEOUT=2
ENV THREADS=12

EXPOSE 389
EXPOSE 8080

# Run it
CMD ["/app/entry.sh"]