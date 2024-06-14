package com.finalyear.VolunteeringSystm.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
// @AllArgsConstructor
public class VolunteerAssignedTaskDto {

    private Integer id;
    private Integer departmentId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String firstName;
    private String status;

    public VolunteerAssignedTaskDto(Integer id, Integer departmentId, String title, String description,
            LocalDate startDate,
            LocalTime startTime, LocalTime finishTime, String firstName, String status) {
        this.id = id;
        this.departmentId = departmentId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.firstName = firstName;
        this.status = status;
    }
}
