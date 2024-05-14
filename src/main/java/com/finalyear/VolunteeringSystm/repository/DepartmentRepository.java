package com.finalyear.VolunteeringSystm.repository;


import com.finalyear.VolunteeringSystm.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByName(String name);
    public boolean existsByName (String name);
}
