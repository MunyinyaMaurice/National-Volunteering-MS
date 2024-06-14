package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.ApplicantDto;
import com.finalyear.VolunteeringSystm.dto.AssignedTaskDto;
import com.finalyear.VolunteeringSystm.dto.VolunteerAssignedTaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.AssignedTask;
import com.finalyear.VolunteeringSystm.model.ApplicantDetails;
import com.finalyear.VolunteeringSystm.model.Role;
import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.TaskStatus;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignedTaskImpl {
    // private final VolunteerRepository volunteerRepository;
    private final AssignedTaskRepository assignedTaskRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public AssignedTask assignTaskToVolunteer(AssignedTaskDto taskAssignmentDto) {
        Task task = taskRepository.findById(taskAssignmentDto.getTaskId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        if (task.getStatus() != TaskStatus.NOW && task.getStatus() != TaskStatus.PENDING) {
            throw new ApplicationException(ErrorCode.BAD_REQUEST, "Task is Ended");
        }

        User volunteer = userRepository.findById(taskAssignmentDto.getVolunteerId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        if (volunteer.getRole() != Role.VOLUNTEER) {
            throw new ApplicationException(ErrorCode.BAD_REQUEST, "User is not a volunteer");
        }

        AssignedTask assignedTask = AssignedTask.builder()
                .task(task)
                .user(volunteer)
                .build();

        return assignedTaskRepository.save(assignedTask);
    }

    public AssignedTask updateAssignedTask(Integer assignedTask_id, AssignedTaskDto assignedTaskDto) {
        AssignedTask assignedTask = assignedTaskRepository.findById(assignedTask_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, " Assigned task not found"));

        User assignedVolunteer = userRepository.findById(assignedTaskDto.getVolunteerId())
                .orElseThrow(
                        () -> new ApplicationException(ErrorCode.NOT_FOUND, " Volunteer to be Assigned not found"));

        Task task = taskRepository.findById(assignedTaskDto.getTaskId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, " Task to be Assigned not found"));

        if (assignedTaskDto.getVolunteerId() != null) {
            assignedTask.setUser(assignedVolunteer);
        }
        if (assignedTaskDto.getTaskId() != null) {
            assignedTask.setTask(task);
        }
        return assignedTaskRepository.save(assignedTask);
    }

    public List<VolunteerAssignedTaskDto> getAllAssignedTasksWithUserDetails() {
        List<Object[]> results = assignedTaskRepository.findAllAssignedTasksWithUserDetails();
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private VolunteerAssignedTaskDto convertToDto(Object[] result) {
        Integer id = (Integer) result[0];
        Integer departmentId = (Integer) result[1];
        String title = (String) result[2];
        String description = (String) result[3];
        LocalDate startDate = convertToLocalDate(result[4]);
        LocalTime startTime = convertToLocalTime(result[5]);
        LocalTime finishTime = convertToLocalTime(result[6]);
        String firstName = (String) result[7];
        String status = (String) result[8];

        return VolunteerAssignedTaskDto.builder()
                .id(id)
                .departmentId(departmentId)
                .title(title)
                .description(description)
                .startDate(startDate)
                .startTime(startTime)
                .finishTime(finishTime)
                .firstName(firstName)
                .status(status)
                .build();
    }

    private LocalDate convertToLocalDate(Object date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return null;
    }

    private LocalTime convertToLocalTime(Object time) {
        if (time instanceof java.sql.Time) {
            return ((java.sql.Time) time).toLocalTime();
        }
        return null;
    }

    public AssignedTask getAssignedTaskById(Integer assTask_id) {
        return assignedTaskRepository.findById(assTask_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Not Assigned task found"));
    }

    public void deleteAssignedTaskById(Integer assTask_id) {
        AssignedTask assignedTask = assignedTaskRepository.findById(assTask_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Not Assigned task found"));
        assignedTaskRepository.delete(assignedTask);
    }

}
