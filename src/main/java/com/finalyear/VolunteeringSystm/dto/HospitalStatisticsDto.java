package com.finalyear.VolunteeringSystm.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
public class HospitalStatisticsDto {
    private String hospitalName;
    private List<DepartmentStatisticsDto> departments;
}