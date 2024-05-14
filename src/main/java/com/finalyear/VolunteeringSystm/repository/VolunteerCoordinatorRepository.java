package com.finalyear.VolunteeringSystm.repository;
import com.finalyear.VolunteeringSystm.model.VolunteerCoordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerCoordinatorRepository extends JpaRepository<VolunteerCoordinator, Integer> {
}
