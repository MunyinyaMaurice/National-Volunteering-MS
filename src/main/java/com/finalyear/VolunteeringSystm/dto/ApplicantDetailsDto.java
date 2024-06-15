
package com.finalyear.VolunteeringSystm.dto;

        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import com.finalyear.VolunteeringSystm.model.Role;

        import jakarta.validation.constraints.Email;

        import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class ApplicantDetailsDto {

  private Integer id;
  private Integer departmentId;
    private String firstName;

    private String lastName;

    private String telPhone;

    private String email;

    private long nationalId;

    private String majorFocus;

    private LocalDate startDate;
    private LocalDate finishDate;

    public ApplicantDetailsDto(Integer id, Integer departmentId, String firstName, String lastName,
                               String telPhone, String email, long nationalId,
                               String majorFocus, LocalDate startDate,
                               LocalDate finishDate) {
        this.id = id;
        this.departmentId = departmentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telPhone = telPhone;
        this.email = email;
        this.nationalId = nationalId;
        this.majorFocus = majorFocus;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}