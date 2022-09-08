package com.norsys.activity.controller;

import com.norsys.activity.dto.UserDto;
import com.norsys.activity.serviceImp.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        Optional<UserDto> userDto = this.userService.getUserById(userId);
        if(userDto.isPresent()){
            return new ResponseEntity<>(userDto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.addUser(userDto);
        if (userDto1 != null) {
            return ResponseEntity.ok().body(userDto);
        }
        else {
            return new ResponseEntity<>(userDto1, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("allCollabs")
    public List<UserRepresentation> getAllCollabs() {
        return userService.getAllCollabs();
    }

    @GetMapping("allAdmins")
    public List<UserRepresentation> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable() String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("the user is deleted");
    }


}