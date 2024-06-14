//package com.finalyear.VolunteeringSystm.model;
//
////import com.finalyear.VolunteeringSystm.model.compositePrimaryKey.VolunteerId;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "volunteers")
//@Entity
//
//public class Volunteer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @OneToOne
//    // @MapsId
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne()
//    @JsonIgnore
//    @JoinColumn(name = "dept_id")
//    private Department department;
//
//    private boolean isActive;
//
//    private Role role;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<AssignedTask> performances;
//}
