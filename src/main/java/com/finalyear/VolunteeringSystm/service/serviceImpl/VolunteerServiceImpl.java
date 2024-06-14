//package com.finalyear.VolunteeringSystm.service.serviceImpl;
//
//import com.finalyear.VolunteeringSystm.dto.AssignedTaskDto;
//import com.finalyear.VolunteeringSystm.dto.VolunteerDto;
//import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
//import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
//import com.finalyear.VolunteeringSystm.model.*;
//import com.finalyear.VolunteeringSystm.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class VolunteerServiceImpl {
//    private final VolunteerRepository volunteerRepository;
//    private final UserRepository userRepository;
//    private final DepartmentRepository departmentRepository;
//
//    public Volunteer createVolunteer(Integer user_id,VolunteerDto volunteerDto){
//        User user = userRepository.findById(user_id)
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"User NotFound"));
//        Department department = departmentRepository.findById(volunteerDto.getDept_id())
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department NotFound"));
//        try {
//            Volunteer volunteer= Volunteer.builder()
//                    .user(user)
//                    .department(department)
//                    .role(Role.VOLUNTEER)
//                    .build();
//          return volunteerRepository.save(volunteer);
//        }catch (ApplicationException e){
//            throw new ApplicationException(ErrorCode.SERVER_ERROR,"something happened while saving volunteer ");
//        }
//    }
//    public Volunteer updateVolunteer(Integer vol_id, VolunteerDto volunteerDto){
//        Volunteer existingVol = volunteerRepository.findById(vol_id)
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Volunteer not found"));
//        Department department = departmentRepository.findById(volunteerDto.getDept_id())
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,"Department not found"));
//       try {
//            existingVol.setDepartment(department);
//            existingVol.setRole(volunteerDto.getRole());
//            return volunteerRepository.save(existingVol);
//       }catch (ApplicationException e){
//           throw new ApplicationException(ErrorCode.SERVER_ERROR,"something happened while updating volunteer ");
//       }
//    }
//    public List<Volunteer> getVolunteers(){
//        return volunteerRepository.findAll();
//    }
//    public Volunteer getVolunteerById( Integer vol_id){
//        return volunteerRepository.findById(vol_id)
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
//    }
//    public void deleteVolunteer(Integer vol_id){
//        Volunteer volunteer = volunteerRepository.findById(vol_id)
//                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
//         volunteerRepository.delete(volunteer);
//    }
//
//}
