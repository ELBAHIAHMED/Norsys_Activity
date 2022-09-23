package com.norsys.activity.dto;

import com.norsys.activity.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String nom;
    private String password;
    private String prenom;
    private String email;
    private final static ModelMapper modelMapper = new ModelMapper();

    public static UserDto getUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}