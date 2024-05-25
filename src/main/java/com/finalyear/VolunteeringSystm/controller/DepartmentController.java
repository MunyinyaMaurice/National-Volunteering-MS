package com.finalyear.VolunteeringSystm.controller;
import com.finalyear.VolunteeringSystm.dto.DepartmentDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.service.serviceImpl.DepartmentServiceImpl;
import com.finalyear.VolunteeringSystm.service.serviceImpl.HospitalServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final HospitalServiceImpl hospitalService;
    private  final DepartmentServiceImpl departmentServiceImpl;
    private final HandleValidationErrors handleValidationErrors;


    @GetMapping
    public ResponseEntity<?> departmentList() {
        List<Department> list = departmentServiceImpl.getDepartments();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{hospital_id}")
    public ResponseEntity<?> createDepartment(@PathVariable Integer hospital_id,
                                              @Valid @RequestBody DepartmentDto departmentDto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            Department createDepartment = departmentServiceImpl.createDepartment(hospital_id,departmentDto);
            return ResponseEntity.ok(createDepartment);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping("/{dept_id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer dept_id) {
        try {
            Department department = departmentServiceImpl.getDepartmentById(dept_id);
            return ResponseEntity.ok(department);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    @PutMapping("/{dept_id}")
    public ResponseEntity<?> updateDepartment(Integer dept_id, @Valid @RequestBody DepartmentDto departmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            Department updatedDepartment = departmentServiceImpl.updateDepartment(dept_id, departmentDto);
            return ResponseEntity.ok(updatedDepartment);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    @DeleteMapping("/{dept_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer dept_id){
        try {
            departmentServiceImpl.deleteDepartmentById(dept_id);
            return ResponseEntity.noContent().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
}

