import { KeycloakService } from 'keycloak-angular';
import config_keycloak from '../../assets/config/config_keycloak.json'

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: config_keycloak.KEYCLOAK_URL,
        realm: config_keycloak.KEYCLOAK_REALM,
        clientId: config_keycloak.KEYCLOAK_CLIENT_ID,
      },
      initOptions: {
        pkceMethod: 'S256',
        redirectUri: config_keycloak.KEYCLOAK_REDIRECT_URI,
        checkLoginIframe: false
      }, 
    });
}
