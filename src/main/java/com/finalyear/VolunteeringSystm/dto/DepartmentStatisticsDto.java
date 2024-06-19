package com.finalyear.VolunteeringSystm.dto;

import lombok.*;

@Data
@Builder
public class DepartmentStatisticsDto {
    private String departmentName;
    private int openPositions;
    private int applicantsApplied;
    private int applicantsToVolunteers;
    private int createdTasks;
    private int assignedTasks;
    private int performedTasks;
    private int activeVolunteers;
}
