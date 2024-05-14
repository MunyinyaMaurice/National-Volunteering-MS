package com.finalyear.VolunteeringSystm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "departments")
@Entity

public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    @ElementCollection
    @CollectionTable(name = "department_requirements", joinColumns = @JoinColumn(name = "department_id"))
    @Column(name = "required_skills")
    private List<String> DepartmentRequirements;

    @OneToMany(mappedBy = "department")
    private List<Volunteer> volunteers;

    @OneToMany(mappedBy = "department")
    private List<VolunteerCoordinator> vol_coordinators;

    @OneToMany(mappedBy = "department")
    private List<Task> tasks;

    @OneToMany(mappedBy = "department")
    private List<OpenPosition> openPositions;
}
