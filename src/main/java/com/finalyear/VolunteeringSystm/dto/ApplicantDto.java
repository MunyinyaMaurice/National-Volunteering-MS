package com.finalyear.VolunteeringSystm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.finalyear.VolunteeringSystm.model.Role;

import jakarta.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDto {
  // @NotNull
  private Integer userId;
  private Integer departmentId;
  private String firstName;
  // @NotNull
  private String lastName;
  // @NotNull
  private String telPhone;
  @Email
  private String email;
  private Role role;
  // @NotNull
  private long nationalId;
  // @NotNull
  private long degreeId;
  // @NotNull
  private String majorFocus;
}
