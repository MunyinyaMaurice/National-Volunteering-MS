package com.finalyear.VolunteeringSystm.repository;


import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
import com.finalyear.VolunteeringSystm.dto.OpenPositionInfoDto;
import com.finalyear.VolunteeringSystm.model.OpenPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenPositionRepository extends JpaRepository<OpenPosition,Integer> {
@Query(value = "SELECT " +
        "o.id AS openPositionsId, " +
        "o.description AS description, " +
        "o.numb_of_vols AS numbOfVols, " +
        "o.start_date AS startDate, " +
        "o.finish_date AS finishDate, " +
        "d.name AS departmentsName, " +
        "GROUP_CONCAT(p.required_skills SEPARATOR ', ') AS requiredSkills, " +
        "h.name AS hospitalsName " +
        "FROM open_positions o " +
        "JOIN departments d ON d.id = o.dept_id " +
        "JOIN hospitals h ON h.id = d.hospital_id " +
        "JOIN department_requirements p ON p.department_id = d.id " +
        "WHERE o.status = 'PENDING' " +
        "GROUP BY o.id, d.name, h.name", nativeQuery = true)
List<Object[]> findPendingOpenPositionsWithSkills();

    @Query(value = "SELECT " +
            "o.id AS openPositionsId, " +
            "o.description AS description, " +
            "o.numb_of_vols AS numbOfVols, " +
            "o.start_date AS startDate, " +
            "o.finish_date AS finishDate, " +
            "d.name AS departmentsName, " +
            "GROUP_CONCAT(p.required_skills SEPARATOR ', ') AS requiredSkills, " +
            "h.name AS hospitalsName " +
            "FROM open_positions o " +
            "JOIN departments d ON d.id = o.dept_id " +
            "JOIN hospitals h ON h.id = d.hospital_id " +
            "JOIN department_requirements p ON p.department_id = d.id " +
            "WHERE o.status = 'PENDING' " +
            "AND o.id = :id " +
            "GROUP BY o.id, d.name, h.name", nativeQuery = true)
    List<Object[]> findPendingOpenPositionsWithSkillsById(@Param("id") Integer id);

}
