package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.HospitalDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.Hospital;
import com.finalyear.VolunteeringSystm.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalServiceImpl {
    private final HospitalRepository hospitalRepository;

    public Hospital createHospital(HospitalDto hospitalDto) {
        if(hospitalDto.getName() ==null || hospitalDto.getLocation() == null
        || hospitalDto.getTelPhone() == null || hospitalDto.getEmail() ==null){
            throw new ApplicationException(ErrorCode.BAD_REQUEST,"All fields are required");
        }
        try {
            if (hospitalRepository.existsByEmail(hospitalDto.getEmail())) {
                throw new ApplicationException(ErrorCode.CONFLICT, "Email provided is already taken");
            }
            if (hospitalRepository.existsByName(hospitalDto.getName())) {
                throw new ApplicationException(ErrorCode.CONFLICT, "Name provided is already taken");
            }
            if (hospitalRepository.existsByTelPhone(hospitalDto.getTelPhone())) {
                throw new ApplicationException(ErrorCode.CONFLICT, "Tel_phone number provided is already taken");
            }
            Hospital hospital = Hospital.builder()
                    .name(hospitalDto.getName())
                    .email(hospitalDto.getEmail())
                    .telPhone(hospitalDto.getTelPhone())
                    .location(hospitalDto.getLocation())
                    .build();
            return hospitalRepository.save(hospital);
        }catch (DataIntegrityViolationException e){
            throw new ApplicationException(ErrorCode.SERVER_ERROR, "Something un expected happened");
        }
        }

    public List<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital updateHospital(Integer hosp_id, HospitalDto hospitalDto) {
        if(hosp_id ==null){
            throw new ApplicationException(ErrorCode.BAD_REQUEST,"Hospital id is required");
        }
            Hospital existingHospital = hospitalRepository.findById(hosp_id)
                    .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Hospital is not found"));

            if (hospitalDto.getEmail() != null) {
                if (!hospitalRepository.existsByEmail(hospitalDto.getEmail())) {
                    existingHospital.setEmail(hospitalDto.getEmail());
                } else {
                    throw new ApplicationException(ErrorCode.CONFLICT, "Email provided is already taken");
                }
            }
            if (hospitalDto.getName() != null) {
                if (!hospitalRepository.existsByName(hospitalDto.getName())) {
                    existingHospital.setName(hospitalDto.getName());
                } else {
                    throw new ApplicationException(ErrorCode.CONFLICT, "Name provided is already taken");
                }
            }
            if (hospitalDto.getTelPhone() != null) {
                if (!hospitalRepository.existsByTelPhone(hospitalDto.getTelPhone())) {
                    existingHospital.setTelPhone(hospitalDto.getTelPhone());
                } else {
                    throw new ApplicationException(ErrorCode.CONFLICT, "Tel_Phone Number provided is already taken");
                }
            }
            if (hospitalDto.getLocation() != null) {
                existingHospital.setLocation(hospitalDto.getLocation());
            }
            return hospitalRepository.save(existingHospital);
    }

    public Hospital getHospitalById(Integer hosp_id) {
        return hospitalRepository.findById(hosp_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Hospital is not found"));
    }
    public void deleteHospitalById(Integer hosp_id) {
        Hospital hospital = hospitalRepository.findById(hosp_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Hospital is not found"));
         hospitalRepository.delete(hospital);

    }

}
