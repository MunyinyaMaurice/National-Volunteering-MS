package com.finalyear.VolunteeringSystm.controller;

import com.finalyear.VolunteeringSystm.dto.HospitalDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
import com.finalyear.VolunteeringSystm.model.Hospital;
import com.finalyear.VolunteeringSystm.service.serviceImpl.HospitalServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hospitals")
 @RequiredArgsConstructor
public class HospitalController {
    private final HospitalServiceImpl hospitalService;
    private final HandleValidationErrors handleValidationErrors;

    @GetMapping
    public ResponseEntity<?> HospitalList() {
        List<Hospital> list = hospitalService.getHospitals();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> createHospital(@Valid @RequestBody HospitalDto hospitalDto,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            Hospital createdHospital = hospitalService.createHospital(hospitalDto);
            return ResponseEntity.ok(createdHospital);
        } catch (ApplicationException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<?> getHospitalById(@PathVariable Integer hospitalId) {
        try {
            Hospital hospital = hospitalService.getHospitalById(hospitalId);
            return ResponseEntity.ok(hospital);
        } catch (ApplicationException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }
    }
    @PutMapping("/{hospitalId}")
    public ResponseEntity<?> updateHospital(Integer hosp_id, @Valid @RequestBody HospitalDto hospitalDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            Hospital updatedHospital = hospitalService.updateHospital(hosp_id, hospitalDto);
            return ResponseEntity.ok(updatedHospital);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }
    public ResponseEntity<?> deleteHospital(@PathVariable Integer hosp_id){
        try {
              hospitalService.deleteHospitalById(hosp_id);
              return ResponseEntity.noContent().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

}
