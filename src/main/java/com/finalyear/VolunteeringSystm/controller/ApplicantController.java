package com.finalyear.VolunteeringSystm.controller;

import com.finalyear.VolunteeringSystm.dto.ApplicantDetailsDto;
import com.finalyear.VolunteeringSystm.dto.ApplicantDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.service.serviceImpl.ApplicantServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final HandleValidationErrors handleValidationErrors;
    private final ApplicantServiceImpl applicantServiceImpl;

    @PostMapping("/{positionId}")
    public ResponseEntity<?> saveApplication(Integer positionId,@Valid @RequestBody ApplicantDto applicantDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors.handleValidationErrors(bindingResult);
        }
        try {
            ApplicantDto applied = applicantServiceImpl.createApplication(positionId,applicantDto);
            return ResponseEntity.ok(applied);
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ApplicantDetailsDto>> getApplicants() {
        List<ApplicantDetailsDto> applicants = applicantServiceImpl.findAllApplicantsInfo();
        return ResponseEntity.ok(applicants);
    }
    @GetMapping("activeApplicants")
    public ResponseEntity<List<ApplicantDetailsDto>> findCurrentlyActiveApplicantsInfo() {
        List<ApplicantDetailsDto> applicants = applicantServiceImpl.findCurrentlyActiveApplicantsInfo();
        return ResponseEntity.ok(applicants);
    }

    // // Get all applicants
    // @GetMapping("/ids")
    // public ResponseEntity<List<Integer>> getApplicantIds() {
    // List<Integer> applicantIds = applicantServiceImpl.getApplicantIds();
    // return ResponseEntity.ok(applicantIds);
    // }

    // // get applicant IDs by a specific ID
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getApplicantIdsByQualificationId(@PathVariable
    // Integer id) {
    // try {
    // List<Integer> applicantIds =
    // applicantServiceImpl.getApplicantIdsByQualificationId(id);
    // return ResponseEntity.ok(applicantIds);
    // } catch (ApplicationException e) {
    // return ResponseEntity.status(e.getErrorCode().getHttpStatus())
    // .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    // }
    // }

    @PutMapping("/role/{applicantId}")
    public ResponseEntity<?> updateRoleToVolunteer(@PathVariable Integer applicantId) {
        try {
            applicantServiceImpl.updateRoleToVolunteer(applicantId);
            return ResponseEntity.ok().build();
        } catch (ApplicationException e) {
            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
        }
    }

    @GetMapping("/volunteers")
    public ResponseEntity<?> getVolunteers() {
        List<User> volunteers = applicantServiceImpl.getVolunteers();
        return ResponseEntity.ok(volunteers);
    }
}
