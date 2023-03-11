package com.mahi.javapassion.coaching.users.security;

import com.mahi.javapassion.coaching.users.User;
import com.mahi.javapassion.coaching.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Represents user details implementation.
 */
@RequiredArgsConstructor
@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);

        return user
                .map(User::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }

}
