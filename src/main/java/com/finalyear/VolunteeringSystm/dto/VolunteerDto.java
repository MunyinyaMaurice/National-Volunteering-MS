package com.finalyear.VolunteeringSystm.dto;
import com.finalyear.VolunteeringSystm.model.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDto {

//    private Integer user_id;

    private Integer dept_id;

    private Role role;
}
