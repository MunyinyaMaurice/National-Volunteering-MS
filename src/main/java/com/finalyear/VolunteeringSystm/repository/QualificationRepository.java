package com.finalyear.VolunteeringSystm.repository;

import com.finalyear.VolunteeringSystm.model.ApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<ApplicantDetails, Integer> {
}
