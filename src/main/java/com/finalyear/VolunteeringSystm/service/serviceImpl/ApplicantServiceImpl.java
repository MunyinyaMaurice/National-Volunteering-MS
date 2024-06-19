package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.ApplicantDetailsDto;
import com.finalyear.VolunteeringSystm.dto.ApplicantDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.*;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import com.finalyear.VolunteeringSystm.repository.OpenPositionRepository;
import com.finalyear.VolunteeringSystm.repository.QualificationRepository;
import com.finalyear.VolunteeringSystm.repository.UserRepository;
import com.finalyear.VolunteeringSystm.util.PasswordGenerator;
import com.finalyear.VolunteeringSystm.util.mail.EmailSenderService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.finalyear.VolunteeringSystm.security.config.ApplicationConfig.getCurrentUser;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl {
    private final UserRepository userRepository;
    private final QualificationRepository qualificationRepository;
    private final DepartmentRepository departmentRepository;
    private final OpenPositionRepository openPositionRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public ApplicantDto createApplication(Integer positionId, ApplicantDto applicantDto) {
        OpenPosition openPosition = openPositionRepository.findById(positionId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
if(openPosition.getStatus() == OpenPosition.PositionStatus.ENDED){
    throw new ApplicationException(ErrorCode.BAD_REQUEST,"This position is no long available");
}

        Integer departmentId = openPosition.getDepartment().getId();
        long nationalId = applicantDto.getNationalId();

        ApplicantDetails existingApplicantDetails = qualificationRepository.findByNationalId(nationalId);

        if (existingApplicantDetails != null) {
            OpenPosition existingOpenPosition = existingApplicantDetails.getOpenPosition();
            if (existingOpenPosition.getDepartment().getId().equals(departmentId)
                    && (existingOpenPosition.getStatus() == OpenPosition.PositionStatus.PENDING
                    || existingOpenPosition.getStatus() == OpenPosition.PositionStatus.ONGOING)) {
                throw new ApplicationException(ErrorCode.BAD_REQUEST,
                        String.format("You are already an active volunteer or Applied for volunteering program" +
                                        " in the department of %s ",
                        existingOpenPosition.getDepartment().getName()));

            } else if (existingOpenPosition.getStatus() == OpenPosition.PositionStatus.ENDED) {
                saveApplicantQualifications(existingApplicantDetails.getUser().getId(), departmentId, applicantDto, openPosition);
                return applicantDto;
            }
        } else {
            User applicant = User.builder()
                    .email(applicantDto.getEmail())
                    .firstName(applicantDto.getFirstName())
                    .lastName(applicantDto.getLastName())
                    .telPhone(applicantDto.getTelPhone())
                    .role(Role.APPLICANT)
                    .build();

            User savedApplicant = userRepository.save(applicant);
            saveApplicantQualifications(savedApplicant.getId(), departmentId, applicantDto, openPosition);
        }

        return applicantDto;
    }

    private void saveApplicantQualifications(Integer applicantId, Integer departmentId, ApplicantDto applicantDto, OpenPosition openPosition) {
        User user = userRepository.findById(applicantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        ApplicantDetails qualification = ApplicantDetails.builder()
                .user(user)
                .degreeId(applicantDto.getDegreeId())
                .nationalId(applicantDto.getNationalId())
                .department(department)
                .openPosition(openPosition)
                .majorFocus(applicantDto.getMajorFocus())
                .build();

        qualificationRepository.save(qualification);
    }

public List<ApplicantDetailsDto> findAllApplicantsInfo() {
    User currentUser = getCurrentUser();
    if(currentUser != null){
    if (currentUser.getRole() != Role.SUPER_ADMIN && currentUser.getRole() != Role.ADMIN
            && currentUser.getRole() != Role.COORDINATOR) {
        throw new ApplicationException(ErrorCode.UNAUTHORIZED,
                "You do not have permission to get list of applicants");
    }

    List<Object[]> results = qualificationRepository.findAllApplicantsInfo();
    return results.stream().map(this::convertToDto).collect(Collectors.toList());
}
throw new ApplicationException(ErrorCode.UNAUTHORIZED,
                "You are not logged-in");
}
    public List<ApplicantDetailsDto> findCurrentlyActiveApplicantsInfo() {
        User currentUser = getCurrentUser();
        if(currentUser != null){
        if (currentUser.getRole() != Role.SUPER_ADMIN && currentUser.getRole() != Role.ADMIN
                && currentUser.getRole() != Role.COORDINATOR) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED,
                    "You do not have permission ");
        }
            List<Object[]> results = qualificationRepository.findCurrentlyActiveApplicantsInfo();
            return results.stream().map(this::convertToDto).collect(Collectors.toList());
        }

        throw new ApplicationException(ErrorCode.UNAUTHORIZED,
                "You are not logged-in");
    }

    private ApplicantDetailsDto convertToDto(Object[] result) {
        Integer id = (Integer) result[0];
        Integer departmentId = (Integer) result[1];
        String firstName = (String) result[2];
        String lastName = (String) result[3];
        String email = (String) result[4];
        String telPhone = (String) result[5];
        long nationalId = (long) result[6];
        String majorFocus = (String) result[7];
        LocalDate startDate = convertToLocalDate(result[8]);
        LocalDate finishDate = convertToLocalDate(result[9]);

        return ApplicantDetailsDto.builder()
                .id(id)
                .departmentId(departmentId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .telPhone(telPhone)
                .nationalId(nationalId)
                .majorFocus(majorFocus)
                .startDate(startDate)
                .finishDate(finishDate)
                .build();
    }
    private LocalDate convertToLocalDate(Object date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return null;
    }

@Transactional
public void updateRoleToVolunteer(Integer applicantId) {
    User user = userRepository.findById(applicantId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

    String randomPassword = PasswordGenerator.generateRandomPassword();
    String encodedPassword = passwordEncoder.encode(randomPassword);

    user.setPassword(encodedPassword);
    user.setRole(Role.VOLUNTEER);
    userRepository.save(user);

    emailSenderService.sendWelcomeEmail(user.getEmail(), randomPassword);
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
