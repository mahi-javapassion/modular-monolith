package com.mahi.javapassion.coaching.users.registration.integration;

import com.mahi.javapassion.coaching.users.Role;
import com.mahi.javapassion.coaching.users.RoleDTO;
import com.mahi.javapassion.coaching.users.User;
import com.mahi.javapassion.coaching.users.UserRepository;
import com.mahi.javapassion.coaching.users.registration.UserRegistrationCommand;
import com.mahi.javapassion.coaching.users.registration.UserRegistrationCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRegistrationCommandHandlerIntegrationTest {

    @Autowired
    private UserRepository repository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @SpyBean
    private UserRegistrationCommandHandler sut;

    @Test
    void handle_validCommand_userCreated() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();

        // when
        sut.handle(userRegistrationCommand);

        // then
        final Optional<User> saved = repository.findByUsername("username");
        assertThat(saved).isNotEmpty();
        final User user = saved.get();
        assertThat(user)
                .hasFieldOrPropertyWithValue("username", "username")
                .hasFieldOrPropertyWithValue("email", "email@gmail.com")
                .hasFieldOrPropertyWithValue("role", Role.ROLE_STUDENT);
    }
}
