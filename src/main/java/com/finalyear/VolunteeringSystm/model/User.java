package com.finalyear.VolunteeringSystm.model;

        import java.util.Collection;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;
        import jakarta.persistence.GenerationType;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicant")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String telPhone;

    private String homeAddress;

    private Integer age;

    private String gender;

    private String status;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Qualification> qualificationList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Volunteer> volunteers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
