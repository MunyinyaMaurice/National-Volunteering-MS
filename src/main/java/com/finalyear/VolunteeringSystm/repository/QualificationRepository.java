package com.finalyear.VolunteeringSystm.repository;
import com.finalyear.VolunteeringSystm.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
}
