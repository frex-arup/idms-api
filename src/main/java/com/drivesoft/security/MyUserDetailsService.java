package com.drivesoft.security;


import com.drivesoft.entity.User;
import com.drivesoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not found!!"));

        String password = Optional.ofNullable(user.getPassword()).orElse("");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                Optional.ofNullable(user.getRole()).map(role -> List.of(new SimpleGrantedAuthority(role.name()))).orElse(new ArrayList<>());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), password,
                simpleGrantedAuthorities);
    }
}