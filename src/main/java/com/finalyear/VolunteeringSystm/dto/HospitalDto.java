package com.finalyear.VolunteeringSystm.dto;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDto {
//@Valid
    @NotNull(message = "Hospital name can not be null")
    @NotBlank
    private String name;
    @NotNull(message = "Hospital Location can not be null")
    @NotBlank
    private String location;

    @NotNull(message = "Hospital email can not be null")
    @NotBlank
    @Email
    private String email;

    @NotNull(message = "Hospital tel_phone number can not be null")
    @NotBlank
    private String telPhone;

}
