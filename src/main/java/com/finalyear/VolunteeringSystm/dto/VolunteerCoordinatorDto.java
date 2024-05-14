package com.finalyear.VolunteeringSystm.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerCoordinatorDto {

    private String firstName;

    private String LastName;

    private String email;

    private String telPhone;

    private String address;

    private int age;

    private String gender;

    private String status;
}
