package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.AssignedTaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.AssignedTask;
import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.model.Volunteer;
import com.finalyear.VolunteeringSystm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignedTaskImpl {
    private final VolunteerRepository volunteerRepository;
    private final AssignedTaskRepository assignedTaskRepository;
    private final TaskRepository taskRepository;

    public AssignedTask assignTask(AssignedTaskDto assignedTaskDto){
        Volunteer volunteer = volunteerRepository.findById(assignedTaskDto.getVol_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Volunteer not found"));
        Task task = taskRepository.findById(assignedTaskDto.getTask_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Task not found"));

       try{
            return AssignedTask.builder()
                    .volunteer(volunteer)
                    .task(task)
                    .taskStatus(assignedTaskDto.getTaskStatus())
                    .build();
        }catch (ApplicationException e){
           throw  new ApplicationException(ErrorCode.SERVER_ERROR,
                   "something happened while assigning task to volunteer");
       }
    }
    public AssignedTask updateAssignedTask(Integer assignedTask_id, AssignedTaskDto assignedTaskDto){
        AssignedTask assignedTask = assignedTaskRepository.findById(assignedTask_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND," Assigned task not found"));

        Volunteer assignedVolunteer = volunteerRepository.findById(assignedTaskDto.getVol_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND," Volunteer to be Assigned not found"));

        Task task = taskRepository.findById(assignedTaskDto.getTask_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND," Task to be Assigned not found"));


        if(assignedTaskDto.getVol_id() !=null ){
            assignedTask.setVolunteer(assignedVolunteer);
        }
        if(assignedTaskDto.getTask_id() !=null ){
            assignedTask.setTask(task);
        }
        return assignedTaskRepository.save(assignedTask);
    }
    public List<AssignedTask> getAssignedTask(){
        return assignedTaskRepository.findAll();
    }
    public AssignedTask getAssignedTaskById(Integer assTask_id){
        return assignedTaskRepository.findById(assTask_id)
                .orElseThrow(() ->new ApplicationException(ErrorCode.NOT_FOUND, "Not Assigned task found"));
    }
    public void deleteAssignedTaskById(Integer assTask_id){
        AssignedTask assignedTask = assignedTaskRepository.findById(assTask_id)
                .orElseThrow(() ->new ApplicationException(ErrorCode.NOT_FOUND, "Not Assigned task found"));
        assignedTaskRepository.delete(assignedTask);
    }

}
