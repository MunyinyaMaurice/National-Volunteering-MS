package com.finalyear.VolunteeringSystm.repository;
import com.finalyear.VolunteeringSystm.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepositoey extends JpaRepository<Volunteer, Integer> {
}
