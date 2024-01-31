package com.ruben.catalogservice.Models.Enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AvailabilityStatus {
    ToBeLaunched("To Be Launched"),
    OnPreOrder("On Pre Order"),
    OnOrder("On Order"),
    Available("Available"),
    Unavailable("Unavailable");

    private final String abbreviation;

    AvailabilityStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @JsonValue
    public String getAbbreviation() {
        return abbreviation;
    }
}
