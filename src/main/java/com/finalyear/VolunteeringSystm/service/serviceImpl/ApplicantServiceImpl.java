package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.UserDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.model.Qualification;
import com.finalyear.VolunteeringSystm.model.User;
import com.finalyear.VolunteeringSystm.model.Volunteer;
import com.finalyear.VolunteeringSystm.repository.UserRepository;
import com.finalyear.VolunteeringSystm.repository.VolunteerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicantServiceImpl {
    private final UserRepository userRepository;
    public User createApplication( UserDto userDto){
        try{
            User applicant = User.builder()
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .age(userDto.getAge())
                    .gender(userDto.getGender())
                    .homeAddress(userDto.getHomeAddress())
                    .telPhone(userDto.getTelPhone())
                    .status(userDto.getStatus())
                    .build();
            return userRepository.save(applicant);
        }catch (ApplicationException e){
            throw new ApplicationException(ErrorCode.SERVER_ERROR);
        }
    }
//    public Qualification checkApplicantQualifications()
}
