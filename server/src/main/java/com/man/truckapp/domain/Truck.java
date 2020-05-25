package com.man.truckapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "tb_truck")
@JsonInclude(Include.NON_NULL)
public class Truck implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "The model field is required.")
    private String model;

    @Column
    @NotNull(message = "The Engine Power field is required.")
    private Integer enginePower;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull(message = "The Range field is required.")
    private RangeType range;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull(message = "The Fuel field is required.")
    private FuelType fuel;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    private List<Segment> segments;

    public Truck(String model, Integer enginePower, FuelType fuel) {
        this.model = model;
        this.enginePower = enginePower;
        this.fuel = fuel;
    }

    public Truck(String model, Integer enginePower, FuelType fuel, List<Segment> segements) {
        this.model = model;
        this.enginePower = enginePower;
        this.fuel = fuel;
        this.segments = segements;
    }

    public Truck() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public RangeType getRange() {
        return range;
    }

    public void setRange(RangeType range) {
        this.range = range;
    }

    public FuelType getFuel() {
        return fuel;
    }

    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }

    public void addSegment(Segment segment) {
        if (this.segments == null)
            this.segments = new ArrayList<>();

        this.segments.add(segment);
    }

    public List<Segment> getSegements() {
        return segments;
    }

    public void setSegements(List<Segment> segements) {
        this.segments = segements;
    }

    public static TruckBuilder builder() {
        return new TruckBuilder();
    }

    public static class TruckBuilder extends Truck {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public TruckBuilder withModel(final String model) {
            setModel(model);
            return this;
        }

        public TruckBuilder withEnginePower(final Integer enginePower) {
            setEnginePower(enginePower);
            return this;
        }

        public TruckBuilder withFuelType(final FuelType fuelType) {
            setFuel(fuelType);
            return this;
        }

        public TruckBuilder withRangeType(final RangeType rangeType){
            setRange(rangeType);
            return this;
        }

        public TruckBuilder addOneSegment(final Segment segment) {
            addSegment(segment);
            return this;
        }

        public Truck build() {
            return new Truck(this.getModel(), this.getEnginePower(), this.getFuel(), this.getSegements());
        }
    }
}