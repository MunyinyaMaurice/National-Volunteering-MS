package com.finalyear.VolunteeringSystm.dto;

import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.TaskStatus;
import com.finalyear.VolunteeringSystm.model.Volunteer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignedTaskDto {
    @NotBlank @NotNull
    private Integer vol_id;

    @NotBlank @NotNull
    private Integer task_id;

    @NotBlank @NotNull
    private TaskStatus taskStatus;
}
