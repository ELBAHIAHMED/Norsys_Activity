package com.norsys.activity.security;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import static org.keycloak.adapters.KeycloakDeploymentBuilder.build;

public class KeycloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:3800/auth/";
    public final static String realm = "norsys";
    final static String clientId = "admin-cli";
    final static String clientSecret = "ffddfe9b-8127-4ded-9d99-432f0a837da3";
    final static String userName = "norsys-admin";
    final static String password = "mysecretpassword";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId).clientSecret(clientSecret).resteasyClient(new ResteasyClientBuilder()
                    .connectionPoolSize(10).build()
                    ).build();

        }
        return keycloak;
    }
}
