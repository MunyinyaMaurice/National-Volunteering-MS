package com.finalyear.VolunteeringSystm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenPositionInfoDto {
    private Integer id;
    private String description;
    private int numbOfVols;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String departmentsName;
    private String requiredSkills;
    private String hospitalsName;
}
