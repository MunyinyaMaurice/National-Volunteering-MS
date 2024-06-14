package com.finalyear.VolunteeringSystm.repository;

import com.finalyear.VolunteeringSystm.model.AssignedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface AssignedTaskRepository extends JpaRepository<AssignedTask, Integer> {
    @Query(value = "SELECT a.id as id, t.dep_id as departmentId, t.title as title, t.description as description, "
            + "t.start_date as startDate, t.start_time as startTime, t.finish_time as finishTime, "
            + "u.first_name as firstName, t.status as status "
            + "FROM vol_assigned_task a "
            + "JOIN task t ON t.id = a.task_id "
            + "JOIN users u ON u.id = a.vol_id", nativeQuery = true)
    List<Object[]> findAllAssignedTasksWithUserDetails();
}
