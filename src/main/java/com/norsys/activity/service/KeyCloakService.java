package com.norsys.activity.service;




import com.norsys.activity.dto.UserDTO;
import com.norsys.activity.security.Credentials;
import com.norsys.activity.security.KeycloakConfig;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor

@Service
public class KeyCloakService {
    public static final String ROLE_ADMIN = "ADMIN_ACTIVITY";
    public static final String ROLE_COLLAB = "COLLAB_ACTIVITY";
    public Response addUser(UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        ArrayList<String > roleGroup = new ArrayList<>();
        roleGroup.add("/activity-users");
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setGroups(roleGroup);
        user.setCredentials(Collections.singletonList(credential));

        user.setEnabled(true);
        UsersResource instance = getInstance();
        Response response = instance.create(user);

        if (response.getStatus() == 201) {

        }

        return response;
    }
    public List<UserRepresentation> getUser(){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.list();
        System.out.println(user);
        return user;

    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm("norsys").users();

    }
}
