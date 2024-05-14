package com.finalyear.VolunteeringSystm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//import java.security.Permission;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.finalyear.VolunteeringSystm.model.Permission.*;

@RequiredArgsConstructor
public enum Role {
    VOLUNTEER(
            Set.of(
                    VOLUNTEER_READ,
                    VOLUNTEER_UPDATE,
                    VOLUNTEER_ORDER
            )
    ),
    COORDINATOR(
            Set.of(
                    COORDINATOR_READ,
                    COORDINATOR_UPDATE,
                    COORDINATOR_DELETE,
                    COORDINATOR_CREATE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    COORDINATOR_READ,
                    COORDINATOR_UPDATE,
                    COORDINATOR_DELETE,
                    COORDINATOR_CREATE
            )
    ),
    APPLICANT(
            Set.of(
                    APPLICANT_READ,
                    APPLICANT_UPDATE,
                    APPLICANT_ORDER
            )
    );

    public static boolean hasRole(Set<Role> roles, Role role) {
        return roles.contains(role);
    }

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
