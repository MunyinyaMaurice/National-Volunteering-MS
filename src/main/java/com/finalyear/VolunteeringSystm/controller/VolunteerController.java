//package com.finalyear.VolunteeringSystm.controller;
//
//import com.finalyear.VolunteeringSystm.dto.VolunteerDto;
//import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
//import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorResponse;
//import com.finalyear.VolunteeringSystm.handleValidation.HandleValidationErrors;
//import com.finalyear.VolunteeringSystm.model.Volunteer;
//import com.finalyear.VolunteeringSystm.service.serviceImpl.VolunteerServiceImpl;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/volunteers")
//@RequiredArgsConstructor
//public class VolunteerController {
//
//    private final VolunteerServiceImpl volunteerServiceImpl;
//    private final HandleValidationErrors handleValidationErrors;
//
//    @PostMapping("/{user_id}")
//    public ResponseEntity<?> createVolunteer(@PathVariable Integer user_id , @Valid @RequestBody VolunteerDto volunteerDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return handleValidationErrors.handleValidationErrors(bindingResult);
//        }
//        try {
//            Volunteer createdVolunteer = volunteerServiceImpl.createVolunteer(user_id,volunteerDto);
//            return ResponseEntity.ok(createdVolunteer);
//        } catch (ApplicationException e) {
//            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
//                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
//        }
//    }
//
//    @PutMapping("/{vol_id}")
//    public ResponseEntity<?> updateVolunteer(@PathVariable Integer vol_id, @Valid @RequestBody VolunteerDto volunteerDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return handleValidationErrors.handleValidationErrors(bindingResult);
//        }
//        try {
//            Volunteer updatedVolunteer = volunteerServiceImpl.updateVolunteer(vol_id, volunteerDto);
//            return ResponseEntity.ok(updatedVolunteer);
//        } catch (ApplicationException e) {
//            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
//                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getVolunteers() {
//        List<Volunteer> volunteers = volunteerServiceImpl.getVolunteers();
//        return ResponseEntity.ok(volunteers);
//    }
//
//    @GetMapping("/{vol_id}")
//    public ResponseEntity<?> getVolunteerById(@PathVariable Integer vol_id) {
//        try {
//            Volunteer volunteer = volunteerServiceImpl.getVolunteerById(vol_id);
//            return ResponseEntity.ok(volunteer);
//        } catch (ApplicationException e) {
//            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
//                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{vol_id}")
//    public ResponseEntity<?> deleteVolunteer(@PathVariable Integer vol_id) {
//        try {
//            volunteerServiceImpl.deleteVolunteer(vol_id);
//            return ResponseEntity.noContent().build();
//        } catch (ApplicationException e) {
//            return ResponseEntity.status(e.getErrorCode().getHttpStatus())
//                    .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
//        }
//    }
//}