package com.norsys.activity.model;

import com.norsys.activity.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private static final ModelMapper modelMapper = new ModelMapper();

    public static User baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("user_id"));
        user.setUsername(resultSet.getString("user_username"));
        user.setNom(resultSet.getString("user_nom"));
        user.setPrenom(resultSet.getString("user_prenom"));
        user.setEmail(resultSet.getString("user_email"));
        return user;
    }

    public static User getUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}