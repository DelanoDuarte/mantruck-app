package com.man.truckapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RangeType {
    @JsonProperty("Heavy Range")
    HEAVY_RANGE("Heavy Range"),
    @JsonProperty("Light Range") 
    LIGHT_RANGE("Light Range");

    private final String range;

    RangeType(String rangeType) {
        this.range = rangeType;
    }

    @Override
    public String toString() {
        return range;
    }

}
