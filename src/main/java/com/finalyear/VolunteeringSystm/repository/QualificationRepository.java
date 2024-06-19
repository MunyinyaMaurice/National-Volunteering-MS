package com.finalyear.VolunteeringSystm.repository;

import com.finalyear.VolunteeringSystm.model.ApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<ApplicantDetails, Integer> {
    @Query(value = "SELECT u.id as id, a.dept_id as departmentId, u.first_name as firstName, u.last_name as lastName, "
            + "u.email as email, u.tel_phone as telPhone, a.national_id as nationalId, a.major_focus as majorFocus, "
            + "o.finish_date as finishDate, o.start_date as startDate "
            + "FROM applicant_details a "
            + "JOIN users u ON u.id = a.user_id "
            + "JOIN departments d ON d.id = a.dept_id "
            + "LEFT JOIN open_positions o ON o.dept_id = d.id",
            nativeQuery = true)
    List<Object[]> findAllApplicantsInfo();

    @Query(value = "SELECT u.id as id, a.dept_id as departmentId, u.first_name as firstName, u.last_name as lastName, "
            + "u.email as email, u.tel_phone as telPhone, a.national_id as nationalId, a.major_focus as majorFocus, "
            + "o.finish_date as finishDate, o.start_date as startDate "
            + "FROM applicant_details a "
            + "JOIN users u ON u.id = a.user_id "
            + "JOIN departments d ON d.id = a.dept_id "
            + "JOIN open_positions o ON o.dept_id = d.id "
            + "WHERE o.start_date <= CURRENT_DATE AND o.finish_date >= CURRENT_DATE",
            nativeQuery = true)
    List<Object[]> findCurrentlyActiveApplicantsInfo();
    @Query("SELECT COUNT(a) > 0 FROM ApplicantDetails a WHERE a.nationalId = :nationalId")
    boolean existsByNationalId(@Param("nationalId") long nationalId);
    @Query("SELECT a FROM ApplicantDetails a WHERE a.nationalId = :nationalId")
    ApplicantDetails findByNationalId(@Param("nationalId") long nationalId);
}

