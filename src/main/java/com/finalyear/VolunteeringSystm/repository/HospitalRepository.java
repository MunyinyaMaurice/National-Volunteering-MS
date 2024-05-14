package com.finalyear.VolunteeringSystm.repository;


import com.finalyear.VolunteeringSystm.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    Optional<Hospital> findByName(String name);
    public boolean existsByName (String name);
    boolean existsByEmail(String email);

    boolean existsByTelPhone(String telPhone);
}
