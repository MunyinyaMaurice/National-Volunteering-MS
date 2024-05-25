package com.finalyear.VolunteeringSystm.controller;

import com.finalyear.VolunteeringSystm.dto.HospitalDto;
import com.finalyear.VolunteeringSystm.dto.TaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
import com.finalyear.VolunteeringSystm.model.Hospital;
import com.finalyear.VolunteeringSystm.model.Task;
import com.finalyear.VolunteeringSystm.service.serviceImpl.TaskServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final HandleValidationErrors handleValidationErrors;
    private final TaskServiceImp taskServiceImp;
    @PostMapping("/{dept_id}")
    public ResponseEntity<?> createTask (Integer dept_id,@Valid @RequestBody TaskDto taskDto, BindingResult bindingResult){
            if (bindingResult.hasErrors()) {
                return handleValidationErrors.handleValidationErrors(bindingResult);
            }
            try {
                Task task = taskServiceImp.createTask(dept_id,taskDto);
                return ResponseEntity.ok(task);
            } catch (ApplicationException e) {
                return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                        .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
            }
    }
    @PutMapping("/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer task_id, @Valid @RequestBody TaskDto taskDto,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try{
            Task task =  taskServiceImp.updateTask(task_id,taskDto);
            return ResponseEntity.ok(task);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<?> getTasks(){
        try{
           List<Task> tasks = taskServiceImp.getTasks();
            return ResponseEntity.ok(tasks);
        }catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    @GetMapping("/{task_id}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer task_id){
        try{
            Task task = taskServiceImp.getTaskById(task_id);
            return ResponseEntity.ok(task);
        }catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    @DeleteMapping("/{task_id}")
    public ResponseEntity<?> deleteHospital(@PathVariable Integer task_id){
        try {
            taskServiceImp.deleteTask(task_id);
            return ResponseEntity.noContent().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }


}
