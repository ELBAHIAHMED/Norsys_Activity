package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.UserDao;
import com.norsys.activity.dto.UserDto;
import com.norsys.activity.model.User;
import com.norsys.activity.security.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    private final ModelMapper modelMapper = new ModelMapper();
    @Value("${realm-admin.server-url}")
    String serverUrl;
    @Value("${realm-admin.username}")
    String username;
    @Value("${realm-admin.password}")
    String password;
    @Value("${realm-admin.realm}")
    String realm;
    @Value("${realm-admin.group-collabs}")
    String groupCollabs;

    @Value("${realm-admin.group-admins}")
    String groupAdmins;
    @Value("${realm-admin.client-id}")
    String clientId;


    public Optional<UserDto> getUserById(String userId) {
        Optional<User> optionalUser = this.userDao.getUserById(userId);
        return optionalUser.map(this::getUserDto);
    }
    private UserDto getUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private Keycloak getKeyCloakRealmResource() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(clientId)
                .username(username)
                .password(password)
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10)
                        .build()).build();
    }

    private List<UserRepresentation> getUsersByGroupe(String group){
        Keycloak keycloak = getKeyCloakRealmResource();
        GroupsResource groupsResource = keycloak.realm(realm).groups();
        // get groupe id
        String groupeId = groupsResource.groups().stream()
                .filter(groupe -> groupe.getName().equals(group))
                .map(GroupRepresentation::getId)
                .findFirst()
                .orElse("");
        //get groupe users for given id
        return groupsResource.group(groupeId).members();
    }
    public List<UserRepresentation> getAllCollabs() {
        return getUsersByGroupe(groupCollabs);
    }
    public List<UserRepresentation> getAllAdmins() {
        return getUsersByGroupe(groupAdmins);
    }

    public UserDto addUser(UserDto userDto){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDto.getPassword());
        ArrayList<String > roleGroup = new ArrayList<>();
        roleGroup.add(groupCollabs);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getNom());
        user.setLastName(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setGroups(roleGroup);
        user.setCredentials(Collections.singletonList(credential));

        user.setEnabled(true);
        UsersResource instance = getInstance();
        Response response = instance.create(user);
        if(response.getStatus() == 201) {
            for (UserRepresentation userRepresentation: this.getAllCollabs()) {
                System.out.println(user.getUsername().toLowerCase() + userRepresentation.getUsername());
                if(userRepresentation.getUsername().equals(user.getUsername().toLowerCase())) {
                    userDto.setId(userRepresentation.getId());
                }
            }
        }

        System.out.println("dto:  "+userDto);

        if (userDto.getId() != null) {
            this.userDao.createUser(User.getUser(userDto));
            return userDto;
        }
        else {
            log.error("user exists");
            return null;
        }
    }

    public UsersResource getInstance(){
        return this.getKeyCloakRealmResource().realm("norsys").users();
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


}