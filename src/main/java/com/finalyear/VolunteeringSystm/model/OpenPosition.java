package com.finalyear.VolunteeringSystm.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "open_positions")
@Entity
public class OpenPosition {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonIgnore
    private Department department;

    @Column(name = "numb_of_Vols")
    private int numberOfVolunteer;

    @Timestamp
    private Date start_date;

    @Timestamp
    private Date finish_date;
}
