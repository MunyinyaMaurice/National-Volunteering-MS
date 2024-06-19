package com.finalyear.VolunteeringSystm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jdk.jfr.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicant_details")
@Entity
public class ApplicantDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    private long nationalId;
    private long degreeId;
    private String majorFocus;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dept_id")
    private Department department;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "open_position_id")
    private OpenPosition openPosition;


    // @Timestamp
    // private Date submitedAt;
}
