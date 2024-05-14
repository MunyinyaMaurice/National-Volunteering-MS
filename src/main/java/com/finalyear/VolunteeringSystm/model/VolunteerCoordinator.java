package com.finalyear.VolunteeringSystm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vol_coordinators")
@Entity

public class VolunteerCoordinator {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer id;

@ManyToOne
@JoinColumn (name = "dept_id")
private Department department;

private String firstName;

private String LastName;

private String email;

@Column(name = "phone_number")
private String telPhone;

private String address;

private int age;

private String gender;

private String status;

}
