package com.finalyear.VolunteeringSystm.repository;


import com.finalyear.VolunteeringSystm.model.OpenPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenPositionRepository extends JpaRepository<OpenPosition,Integer> {

}
