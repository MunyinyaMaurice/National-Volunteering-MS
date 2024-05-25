package com.finalyear.VolunteeringSystm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotNull @NotBlank
    private Integer dept_id;
    @NotNull @NotBlank
    private String title;
    @NotNull @NotBlank
    private String Description;
}
