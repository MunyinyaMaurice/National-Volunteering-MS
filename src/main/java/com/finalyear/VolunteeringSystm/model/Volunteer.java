package com.finalyear.VolunteeringSystm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "volunteers")
@Entity

public class Volunteer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    private Role role;

    @OneToMany(mappedBy = "volunteer")
    private List<VolunteerPerformance> performances;
}



