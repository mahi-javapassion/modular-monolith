package com.mahi.javapassion.coaching.users.registration;

import com.mahi.javapassion.coaching.users.RoleDTO;
import com.mahi.javapassion.coaching.users.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents User Registration Command.
 */
@Builder
@Data
@AllArgsConstructor
public class UserRegistrationCommand {

    @NotNull
    private final RoleDTO role;

    @Size(min = 4, max = 255)
    @NotBlank
    private final String username;

    @Email
    @NotBlank
    private final String email;

    @ValidPassword
    @NotBlank
    private final String password;

}
