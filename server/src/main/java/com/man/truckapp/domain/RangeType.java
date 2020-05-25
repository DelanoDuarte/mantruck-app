package com.man.truckapp.domain;

public enum RangeType {
    HEAVY_RANGE("Heavy Range"), LIGHT_RANGE("Light Range");

    private final String range;

    RangeType(String rangeType) {
        this.range = rangeType;
    }

    @Override
    public String toString() {
        return range;
    }

}
