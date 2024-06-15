package com.finalyear.VolunteeringSystm.service.schedules;

import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.TaskStatus;
import com.finalyear.VolunteeringSystm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/* THIS METHOD WILL MAKE SURE THAT EVERY 1 MINUTES IF THE IS CHANGE IN WILL
 UPDATE TASK STATUS
 */

@Service
@RequiredArgsConstructor
public class TaskStatusUpdater {
    private final TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void updateTaskStatus() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        for (Task task : tasks) {
            if (task.getStartDate().isEqual(currentDate)) {
                if (task.getStartTime().isBefore(currentTime) && task.getFinishTime().isAfter(currentTime)) {
                    task.setStatus(TaskStatus.NOW);
                } else if (task.getFinishTime().isBefore(currentTime)) {
                    task.setStatus(TaskStatus.ENDED);
                }
            } else if (task.getStartDate().isBefore(currentDate)) {
                task.setStatus(TaskStatus.ENDED);
            }
            taskRepository.save(task);
        }
    }
}