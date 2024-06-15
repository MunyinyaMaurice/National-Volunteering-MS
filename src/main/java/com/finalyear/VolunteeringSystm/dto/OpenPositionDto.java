
package com.finalyear.VolunteeringSystm.dto;

import com.finalyear.VolunteeringSystm.handleValidation.customAnnotation.DateRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DateRange(message = "Start date must be before finish date")
public class OpenPositionDto {

    private Integer departmentId;

    private int numberOfVolunteer;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate finishDate;
}


