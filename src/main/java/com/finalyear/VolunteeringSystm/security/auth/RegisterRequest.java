package com.finalyear.VolunteeringSystm.security.auth;

import com.finalyear.VolunteeringSystm.model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Valid
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private int age;
    @NotNull
    private String gender;
    @NotNull
    private String status;
    @NotNull
    private String telPhone;
    @NotNull
    private String homeAddress;

    @NotNull @NotBlank
    @Email
    @NotNull @NotBlank
    @Email
    private String email;
    @NotNull @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()-+=]).{6,14}$",
            message = "Password must be 6 to 14 characters long and contain at least one uppercase letter and one special character.")
    private String password;

    private Role role;
}