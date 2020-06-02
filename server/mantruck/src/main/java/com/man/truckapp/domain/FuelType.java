package com.man.truckapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FuelType {
    @JsonProperty("Gasoline")
    GASOLINE,
    @JsonProperty("Diesel")
    DIESEL,
    @JsonProperty("Off Road Diesel")
    OFF_ROAD_DIESEL,
    @JsonProperty("Bio Diesel")
    BIO_DIESEL,
    @JsonProperty("Propane")
    PROPANE,
    @JsonProperty("Ethanol")
    ETHANOL
}
