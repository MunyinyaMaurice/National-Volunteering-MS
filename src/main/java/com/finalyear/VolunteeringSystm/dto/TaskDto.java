package com.finalyear.VolunteeringSystm.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Integer dept_id;
    private String title;
    private String Description;
}
