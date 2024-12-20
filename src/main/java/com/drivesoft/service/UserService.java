package com.drivesoft.service;

import com.drivesoft.constant.Role;
import com.drivesoft.dto.UserDto;
import com.drivesoft.entity.User;
import com.drivesoft.exception.DuplicateResourceException;
import com.drivesoft.exception.ResourceNotFoundException;
import com.drivesoft.mapper.UserMapper;
import com.drivesoft.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.admin.username}")
    private String adminUsername;

    @Value("${application.admin.password}")
    private String adminPassword;

    @Value("${application.admin.email}")
    private String adminEmail;

    @Value("${application.admin.role}")
    private String adminRole;

    /**
     * Create a new user.
     *
     * @param userDto the user data transfer object containing user details
     * @return the created user details
     */
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername()) ||
                userRepository.existsByEmail(userDto.getEmail()) ||
                userRepository.existsByMobile(userDto.getMobile())) {
            throw new DuplicateResourceException("Username, email, or mobile already exists");
        }
        Preconditions.checkArgument(StringUtils.isNotBlank(userDto.getPassword()), "Password cannot be blank");
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Get user details by ID.
     *
     * @param id the user ID
     * @return the user details
     */
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    /**
     * Update an existing user.
     *
     * @param id      the user ID
     * @param userDto the user data transfer object containing updated details
     * @return the updated user details
     */
    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setRole(userDto.getRole());
        if (StringUtils.isNotBlank(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Delete a user by ID.
     *
     * @param id the user ID
     */
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    /**
     * Create an admin user using properties.
     *
     * @return the created admin user details
     */
    public UserDto createAdminUser() {
        if (userRepository.existsByUsername(adminUsername) ||
                userRepository.existsByEmail(adminEmail)) {
            throw new DuplicateResourceException("Admin user already exists");
        }

        User admin = new User();
        admin.setUsername(adminUsername);
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.valueOf(adminRole));
        admin = userRepository.save(admin);
        return userMapper.toDto(admin);
    }

}
