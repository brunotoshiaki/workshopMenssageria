FROM quay.io/keycloak/keycloak:latest
RUN /opt/keycloak/bin/kc.sh 
COPY realm-export.json /opt/keycloak/data/import/realm-export.json 

