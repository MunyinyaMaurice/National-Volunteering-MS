package com.finalyear.VolunteeringSystm.controller;

import com.finalyear.VolunteeringSystm.dto.AssignedTaskDto;
import com.finalyear.VolunteeringSystm.dto.VolunteerAssignedTaskDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
import com.finalyear.VolunteeringSystm.model.AssignedTask;
import com.finalyear.VolunteeringSystm.service.serviceImpl.AssignedTaskImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignedTasks")
@RequiredArgsConstructor
public class AssignedTaskController {

    private final AssignedTaskImpl assignedTaskImpl;
    private final HandleValidationErrors handleValidationErrors;

    @PostMapping
    public ResponseEntity<?> assignTask(@Valid @RequestBody AssignedTaskDto assignedTaskDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            AssignedTask assignedTask = assignedTaskImpl.assignTaskToVolunteer(assignedTaskDto);
            return ResponseEntity.ok(assignedTask);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @PutMapping("/{assignedTask_id}")
    public ResponseEntity<?> updateAssignedTask(@PathVariable Integer assignedTask_id,
            @Valid @RequestBody AssignedTaskDto assignedTaskDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            AssignedTask updatedAssignedTask = assignedTaskImpl.updateAssignedTask(assignedTask_id, assignedTaskDto);
            return ResponseEntity.ok(updatedAssignedTask);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    // @GetMapping
    // public ResponseEntity<List<VolunteerAssignedTaskDto>>
    // getAllAssignedTasksWithUserDetails() {
    // List<VolunteerAssignedTaskDto> assignedTasks =
    // assignedTaskImpl.getAllAssignedTasksWithUserDetails();
    // return ResponseEntity.ok(assignedTasks);
    // }

    @GetMapping("/assigned")
    public ResponseEntity<List<VolunteerAssignedTaskDto>> getAllAssignedTasksWithUserDetails() {
        List<VolunteerAssignedTaskDto> assignedTasks = assignedTaskImpl.getAllAssignedTasksWithUserDetails();
        return ResponseEntity.ok(assignedTasks);
    }

    @GetMapping("/{assTask_id}")
    public ResponseEntity<?> getAssignedTaskById(@PathVariable Integer assTask_id) {
        try {
            AssignedTask assignedTask = assignedTaskImpl.getAssignedTaskById(assTask_id);
            return ResponseEntity.ok(assignedTask);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{assTask_id}")
    public ResponseEntity<?> deleteAssignedTask(@PathVariable Integer assTask_id) {
        try {
            assignedTaskImpl.deleteAssignedTaskById(assTask_id);
            return ResponseEntity.noContent().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
}