package com.mahi.javapassion.coaching.users.login;

import com.mahi.javapassion.coaching.common.exception.UnprocessableEntityException;
import com.mahi.javapassion.coaching.users.Role;
import com.mahi.javapassion.coaching.users.UserRepository;
import com.mahi.javapassion.coaching.users.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

/**
 * Represents Sign In command handler.
 */
@RequiredArgsConstructor
@Component
public class SignInCommandHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final Validator validator;
    private final AuthenticationManager authenticationManager;

    /**
     * Handles sign in command. Authenticates and returns token.
     *
     * @param command command
     * @return token
     * @throws ConstraintViolationException validation errors
     * @throws UnprocessableEntityException invalid username/password
     */
    @CommandHandler
    @NonNull
    public String handle(SignInCommand command) {
        try {
            final Set<ConstraintViolation<SignInCommand>> violations = validator.validate(command);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword()));
            return jwtTokenProvider.createToken(command.getUsername(), Collections.singletonList(Role.from(repository.findByUsername(command.getUsername()).get().toDTO().getRole())));
        } catch (AuthenticationException e) {
            throw new UnprocessableEntityException("Invalid username/password");
        }
    }
}
