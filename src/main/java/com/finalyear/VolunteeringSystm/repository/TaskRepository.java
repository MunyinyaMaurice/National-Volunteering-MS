package com.finalyear.VolunteeringSystm.repository;
import com.finalyear.VolunteeringSystm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
