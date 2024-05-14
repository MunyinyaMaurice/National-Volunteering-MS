package com.finalyear.VolunteeringSystm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    COORDINATOR_READ("seller:read"),
    COORDINATOR_UPDATE("seller:update"),
    COORDINATOR_CREATE("seller:create"),
    COORDINATOR_DELETE("seller:delete"),

    VOLUNTEER_READ("buyer:read"),
    VOLUNTEER_UPDATE("buyer:order"),
    VOLUNTEER_ORDER("buyer_order"),

    APPLICANT_READ("buyer:read"),
    APPLICANT_UPDATE("buyer:order"),
    APPLICANT_ORDER("buyer_order")

    ;

    @Getter
    private final String permission;
}


