package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
import com.finalyear.VolunteeringSystm.dto.OpenPositionInfoDto;
import com.finalyear.VolunteeringSystm.dto.VolunteerAssignedTaskDto;
import com.finalyear.VolunteeringSystm.model.Role;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import com.finalyear.VolunteeringSystm.model.OpenPosition;
import com.finalyear.VolunteeringSystm.repository.OpenPositionRepository;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public  List<OpenPositionInfoDto> getOPenPositionById(Integer positionId){
        List<Object[]> results = openPositionRepository.findPendingOpenPositionsWithSkillsById(positionId);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<OpenPositionInfoDto> getPendingOpenPositionsWithSkills() {
        List<Object[]> results = openPositionRepository.findPendingOpenPositionsWithSkills();
        return results.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private OpenPositionInfoDto convertToDto(Object[] result) {
        Integer id = (Integer) result[0];
        String description = (String) result[1];
        Integer numbOfVols = (Integer) result[2];
        LocalDate finishDate = convertToLocalDate(result[3]);
        LocalDate startDate = convertToLocalDate(result[4]);
        String departmentsName = (String) result[5];
        String requiredSkills = (String) result[6];
        String hospitalsName = (String) result[7];

        return OpenPositionInfoDto.builder()
                .id(id)
                .description(description)
                .numbOfVols(numbOfVols)
                .startDate(startDate)
                .finishDate(finishDate)
                .departmentsName(departmentsName)
                .requiredSkills(requiredSkills)
                .hospitalsName(hospitalsName)
                .build();
    }

    private LocalDate convertToLocalDate(Object date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return null;
    }



}
