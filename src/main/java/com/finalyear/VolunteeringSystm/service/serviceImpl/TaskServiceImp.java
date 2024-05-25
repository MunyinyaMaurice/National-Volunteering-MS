package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.TaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.model.Volunteer;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import com.finalyear.VolunteeringSystm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImp {
    private final DepartmentRepository departmentRepository;
    private final TaskRepository taskRepository;

    public Task createTask(Integer dept_id ,TaskDto taskDto) {
        Department department = departmentRepository.findById(dept_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department not found!"));

        try {
            Task createdTask = Task.builder()
                    .title(taskDto.getTitle())
                    .Description(taskDto.getDescription())
                    .department(department)
                    .build();
            return taskRepository.save(createdTask);
        } catch (ApplicationException e) {
            throw new ApplicationException(ErrorCode.SERVER_ERROR);
        }
    }
    public Task updateTask(Integer task_id ,TaskDto taskDto) {
        Task existingTask = taskRepository.findById(task_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Task not found!"));

        Department existingDepartment = departmentRepository.findById(taskDto.getDept_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department not found!"));

        if(taskDto.getTitle() !=null){
            existingTask.setTitle(taskDto.getTitle());
        }
        if(taskDto.getDescription() !=null){
            existingTask.setDescription(taskDto.getDescription());
        }
        if(taskDto.getDept_id() !=null){
            existingTask.setDepartment(existingDepartment);
        }

        try {
            return taskRepository.save(existingTask);
        } catch (ApplicationException e) {
            throw new ApplicationException(ErrorCode.SERVER_ERROR);
        }
    }
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Integer task_id){
        return taskRepository.findById(task_id)
                .orElseThrow(() ->new ApplicationException(ErrorCode.NOT_FOUND, "Task not found"));
    }
    public void deleteTask(Integer task_id){
       Task existingTask = taskRepository.findById(task_id)
                .orElseThrow(() ->new ApplicationException(ErrorCode.NOT_FOUND, "Task not found"));
        taskRepository.delete(existingTask);
    }
}
