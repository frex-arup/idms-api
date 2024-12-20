package com.drivesoft.mapper;

import com.drivesoft.dto.UserDto;
import com.drivesoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDto> {

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobile(user.getMobile());
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        userDto.setCreatedBy(user.getCreatedBy());
        userDto.setUpdatedBy(user.getUpdatedBy());
        userDto.setCreatedOn(user.getCreatedOn());
        userDto.setUpdatedOn(user.getUpdatedOn());
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setRole(userDto.getRole());
        user.setId(userDto.getId());
        user.setCreatedBy(userDto.getCreatedBy());
        user.setUpdatedBy(userDto.getUpdatedBy());
        user.setCreatedOn(userDto.getCreatedOn());
        user.setUpdatedOn(userDto.getUpdatedOn());
        return user;
    }
}
