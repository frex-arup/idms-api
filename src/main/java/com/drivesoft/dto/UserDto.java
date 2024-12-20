package com.drivesoft.dto;

import com.drivesoft.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDTO {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private Role role;
}
