package com.finalyear.VolunteeringSystm.repository;

import com.finalyear.VolunteeringSystm.model.Role;
import com.finalyear.VolunteeringSystm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(Role role);
}
