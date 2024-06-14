package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.TaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.TaskStatus;
import com.finalyear.VolunteeringSystm.model.User;
//import com.finalyear.VolunteeringSystm.model.Volunteer;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import com.finalyear.VolunteeringSystm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImp {
    private final DepartmentRepository departmentRepository;
    private final TaskRepository taskRepository;

    // public Task createTask(Integer dept_id ,TaskDto taskDto) {
    // Department department = departmentRepository.findById(dept_id)
    // .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department
    // not found!"));

    // try {
    // Task createdTask = Task.builder()
    // .title(taskDto.getTitle())
    // .Description(taskDto.getDescription())
    // .department(department)
    // .build();
    // return taskRepository.save(createdTask);
    // } catch (ApplicationException e) {
    // throw new ApplicationException(ErrorCode.SERVER_ERROR);
    // }
    // }
    // public Task updateTask(Integer task_id ,TaskDto taskDto) {
    // Task existingTask = taskRepository.findById(task_id)
    // .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Task not
    // found!"));

    // Department existingDepartment =
    // departmentRepository.findById(taskDto.getDept_id())
    // .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department
    // not found!"));

    // if(taskDto.getTitle() !=null){
    // existingTask.setTitle(taskDto.getTitle());
    // }
    // if(taskDto.getDescription() !=null){
    // existingTask.setDescription(taskDto.getDescription());
    // }
    // if(taskDto.getDept_id() !=null){
    // existingTask.setDepartment(existingDepartment);
    // }

    // try {
    // return taskRepository.save(existingTask);
    // } catch (ApplicationException e) {
    // throw new ApplicationException(ErrorCode.SERVER_ERROR);
    // }
    // }

    public Task createTask(TaskDto taskDto) {
        Department departmentOpt = departmentRepository.findById(taskDto.getDepartmentId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        Task task = Task.builder()
                .department(departmentOpt)
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .startDate(taskDto.getStartDate())
                .startTime(taskDto.getStartTime())
                .finishTime(taskDto.getFinishTime())
                .status(TaskStatus.PENDING) // Set initial status to PENDING
                .build();

        return taskRepository.save(task);
    }

    public Task updateTask(@PathVariable Integer id, TaskDto taskDto) {
        Task taskOpt = taskRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        Task task = taskOpt;
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStartDate(taskDto.getStartDate());
        task.setStartTime(taskDto.getStartTime());
        task.setFinishTime(taskDto.getFinishTime());

        return taskRepository.save(task);

    }

    // THIS METHOD WILL MAKE SURE THAT EVERY 1 MUNITES IF THE IS CHANGE IN WILL
    // UPDATE TASK STATUS
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

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer task_id) {
        return taskRepository.findById(task_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Task not found"));
    }

    public void deleteTask(Integer task_id) {
        Task existingTask = taskRepository.findById(task_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Task not found"));
        taskRepository.delete(existingTask);
    }
}
