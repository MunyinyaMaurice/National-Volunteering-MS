package com.finalyear.VolunteeringSystm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dep_id")
    private Department department;

    private String title;
    private String description;

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime finishTime;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToMany(mappedBy = "task")
    private List<AssignedTask> assignedTask;
}

// enum TaskStatus {
// PENDING, NOW, ENDED
// }
