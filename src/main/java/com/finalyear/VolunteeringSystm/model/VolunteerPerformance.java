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
@Table(name = "vol_performance")
@Entity

public class VolunteerPerformance {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vol_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column (name = "task_status")
    private TaskStatus taskStatus;


}
