package com.finalyear.VolunteeringSystm.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull @NotBlank
    private String firstName;
    @NotNull @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotNull @NotBlank
    private Integer age;
    @NotNull @NotBlank
    private String gender;
    @NotNull @NotBlank
    private String status;
    @NotNull @NotBlank
    private String homeAddress;
    @NotNull @NotBlank
    private String telPhone;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()-+=]).{6,14}$",
            message = "Password must be 6 to 14 characters long and contain at least one uppercase letter and one special character.")
    private String password;
}
