package com.norsys.activity.controller;

import com.norsys.activity.dto.UserDTO;
import com.norsys.activity.service.KeyCloakService;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "user")
public class KeyCloakController {
    @Autowired
    KeyCloakService service;
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
       service.addUser(userDTO);
        System.out.println(userDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserRepresentation>> getUser() {
        List<UserRepresentation> user = service.getUser();
        return ResponseEntity.ok().body(user);
    }
//
//    @PutMapping(path = "/update/{userId}")
//    public String updateUser(@PathVariable("userId") String userId,   @RequestBody UserDTO userDTO){
//        service.updateUser(userId, userDTO);
//        return "User Details Updated Successfully.";
//    }
//
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return ResponseEntity.ok().body("the user is deleted");
    }
//
//    @GetMapping(path = "/verification-link/{userId}")
//    public String sendVerificationLink(@PathVariable("userId") String userId){
//        service.sendVerificationLink(userId);
//        return "Verification Link Send to Registered E-mail Id.";
//    }
//
//    @GetMapping(path = "/reset-password/{userId}")
//    public String sendResetPassword(@PathVariable("userId") String userId){
//        service.sendResetPassword(userId);
//        return "Reset Password Link Send Successfully to Registered E-mail Id.";
//    }
}
