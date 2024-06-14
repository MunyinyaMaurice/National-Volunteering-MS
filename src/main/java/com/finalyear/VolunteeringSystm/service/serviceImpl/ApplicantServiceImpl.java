package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.ApplicantDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.ApplicantDetails;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.model.Role;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import com.finalyear.VolunteeringSystm.repository.QualificationRepository;
import com.finalyear.VolunteeringSystm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl {
    private final UserRepository userRepository;
    private final QualificationRepository qualificationRepository;
    private final DepartmentRepository departmentRepository;

    public ApplicantDto createApplication(ApplicantDto applicantDto) {
        try {
            User applicant = User.builder()
                    .email(applicantDto.getEmail())
                    .firstName(applicantDto.getFirstName())
                    .lastName(applicantDto.getLastName())
                    .telPhone(applicantDto.getTelPhone())
                    .role(Role.APPLICANT)
                    .build();

            User savedApplicant = userRepository.save(applicant);

            saveApplicantQualifications(savedApplicant.getId(), applicantDto);

            return applicantDto;
        } catch (ApplicationException e) {
            throw new ApplicationException(ErrorCode.SERVER_ERROR);
        }
    }

    private ApplicantDetails saveApplicantQualifications(Integer applicantId, ApplicantDto applicantDto) {
        User user = userRepository.findById(applicantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
        Department department = departmentRepository.findById(applicantDto.getDepartmentId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        ApplicantDetails qualification = ApplicantDetails.builder()
                .user(user)
                .degreeId(applicantDto.getDegreeId())
                .nationalId(applicantDto.getNationalId())
                .department(department)
                .majorFocus(applicantDto.getMajorFocus())
                .build();

        return qualificationRepository.save(qualification);
    }

    public List<ApplicantDto> getApplicants() {
        List<ApplicantDetails> qualifications = qualificationRepository.findAll();
        // return qualifications.stream().map(qualification -> {
        // User user = qualification.getUser();
        // Department department = qualification.getDepartment();
        // return new ApplicantDto(
        // user.getId(),
        // user.getFirstName(),
        // user.getLastName(),
        // user.getTelPhone(),
        // user.getEmail(),
        // user.getRole(),
        // department.getId(),
        // qualification.getNationalId(),
        // qualification.getDepartment(),
        // qualification.getMajorFocus());

        // }).collect(Collectors.toList());
        return null;
    }

    public void updateRoleToVolunteer(Integer applicantId) {
        User user = userRepository.findById(applicantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        user.setRole(Role.VOLUNTEER);
        userRepository.save(user);
    }

    public List<User> getVolunteers() {
        return userRepository.findUsersByRole(Role.VOLUNTEER);
    }

    // // get all applicants with qualifications
    // public List<Integer> getApplicantIds() {
    // return qualificationRepository.findAll().stream()
    // .map(qualification -> qualification.getUser().getId())
    // .distinct()
    // .collect(Collectors.toList());
    // }

    // // Get applicant by there id
    // public List<Integer> getApplicantIdsByQualificationId(Integer id) {
    // return qualificationRepository.findById(id)
    // .map(qualification -> List.of(qualification.getUser().getId()))
    // .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
    // }

}
