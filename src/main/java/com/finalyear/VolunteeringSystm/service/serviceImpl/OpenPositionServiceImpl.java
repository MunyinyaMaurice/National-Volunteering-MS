package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
import com.finalyear.VolunteeringSystm.model.Role;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import com.finalyear.VolunteeringSystm.model.OpenPosition;
import com.finalyear.VolunteeringSystm.repository.OpenPositionRepository;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;

import lombok.RequiredArgsConstructor;

import static com.finalyear.VolunteeringSystm.security.config.ApplicationConfig.getCurrentUser;

@Service
@RequiredArgsConstructor
public class OpenPositionServiceImpl {
    private final OpenPositionRepository openPositionRepository;
    private final DepartmentRepository departmentRepository;

    public OpenPosition createPosition(Integer departmentId, OpenPositionDto openPositionDto) {
        User currentUser = getCurrentUser();
        if (currentUser.getRole() != Role.SUPER_ADMIN && currentUser.getRole() != Role.ADMIN
                && currentUser.getRole() != Role.COORDINATOR) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED,
                    "You do not have permission to create a position");
        }

        OpenPosition openPosition = OpenPosition.builder()
                .numberOfVolunteer(openPositionDto.getNumberOfVolunteer())
                .description(openPositionDto.getDescription())
                .startDate(openPositionDto.getStartDate())
                .finishDate(openPositionDto.getFinishDate())
                .status(OpenPosition.PositionStatus.PENDING)
                .build();

        return departmentRepository.findById(departmentId)
                .map(department -> {
                    openPosition.setDepartment(department);
                    return openPositionRepository.save(openPosition);
                })
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Department not found"));
    }


//    public OpenPosition createPosition(Integer departmentId, OpenPosition openPosition) {
//        User currentUser = getCurrentUser();
//        if (currentUser.getRole() != Role.SUPER_ADMIN && currentUser.getRole() != Role.ADMIN
//                && currentUser.getRole() != Role.COORDINATOR) {
//            throw new ApplicationException(ErrorCode.UNAUTHORIZED,
//                    "You do not have permission to create an position");
//        }
//        return departmentRepository.findById(departmentId)
//                .map(department -> {
//                    openPosition.setDepartment(department);
//                    return openPositionRepository.save(openPosition);
//                })
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Department not found"));
//    }

}
