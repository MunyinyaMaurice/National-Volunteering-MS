package com.finalyear.VolunteeringSystm.service.serviceImpl;

import com.finalyear.VolunteeringSystm.dto.DepartmentDto;
import com.finalyear.VolunteeringSystm.exceptionHandler.ApplicationException;
import com.finalyear.VolunteeringSystm.exceptionHandler.ErrorCode;
import com.finalyear.VolunteeringSystm.model.Department;
import com.finalyear.VolunteeringSystm.model.Hospital;
import com.finalyear.VolunteeringSystm.repository.DepartmentRepository;
import com.finalyear.VolunteeringSystm.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public Department createDepartment(Integer hospital_id,DepartmentDto departmentDto){
        Hospital hospital = hospitalRepository.findById(hospital_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Hospital not found"));
        try{
            if (departmentRepository.existsByName(departmentDto.getName())) {
                throw new ApplicationException(ErrorCode.CONFLICT, "Department name is already taken");
            }
            Department dept = Department.builder()
                    .name(departmentDto.getName())
                    .DepartmentRequirements(departmentDto.getRequired_skills())
                    .hospital(hospital)
                    .build();
            return departmentRepository.save(dept);
        }catch (ApplicationException e){
            throw new ApplicationException(ErrorCode.SERVER_ERROR);
        }
    }
    public Department updateDepartment(Integer dept_id ,DepartmentDto departmentDto){
        Department existingDepartment = departmentRepository.findById(dept_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Department not found"));
        Hospital existingHospital = hospitalRepository.findById(departmentDto.getHospital_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Hospital not found"));

            if (departmentDto.getName() != null) {
                if (!departmentRepository.existsByName(departmentDto.getName())) {
                    existingDepartment.setName(departmentDto.getName());
                } else {
                    throw new ApplicationException(ErrorCode.CONFLICT, "Name provided is already taken");
                }
            }
//        try{
            if (departmentDto.getHospital_id() != null) {
                existingDepartment.setHospital(existingHospital);
            }
            if (departmentDto.getRequired_skills() != null) {
                existingDepartment.setDepartmentRequirements(departmentDto.getRequired_skills());
            }
          return departmentRepository.save(existingDepartment);

}
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    public Department getDepartmentById(Integer dept_id) {
        return departmentRepository.findById(dept_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Department is not found"));
    }
    public void deleteDepartmentById(Integer dept_id) {
        Department department = departmentRepository.findById(dept_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND, "Department is not found"));
        departmentRepository.delete(department);

    }

}