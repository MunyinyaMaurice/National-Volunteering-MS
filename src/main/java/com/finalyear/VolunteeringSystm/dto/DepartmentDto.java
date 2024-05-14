package com.finalyear.VolunteeringSystm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private Integer hospital_id;
    @NotNull
    @NotBlank
    private List<String> required_skills;
}
