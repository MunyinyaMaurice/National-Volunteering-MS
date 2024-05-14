package com.finalyear.VolunteeringSystm.model;

import jdk.jfr.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@Entity
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

    private String title;
    private String Description;

    @Timestamp
    private Date start_date;

    @Timestamp
    private Date finish_date;

    @OneToMany(mappedBy = "task")
    private List<VolunteerPerformance> taskPerformed;
}
