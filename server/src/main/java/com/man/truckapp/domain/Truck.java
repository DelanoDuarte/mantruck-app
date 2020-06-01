package com.man.truckapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @Basic
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trucks_segments", joinColumns = @JoinColumn(name = "truck_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
    private List<Segment> segments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "trucks_colors", joinColumns = @JoinColumn(name = "truck_id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors = new ArrayList<>();

    public Truck(String model, Integer enginePower, FuelType fuel) {
        this.model = model;
        this.enginePower = enginePower;
        this.fuel = fuel;
    }

    public Truck(@NotNull(message = "The model field is required.") String model,
            @NotNull(message = "The Engine Power field is required.") Integer enginePower,
            @NotNull(message = "The Range field is required.") RangeType range,
            @NotNull(message = "The Fuel field is required.") FuelType fuel, List<Segment> segments,
            List<Color> colors) {
        this.model = model;
        this.enginePower = enginePower;
        this.range = range;
        this.fuel = fuel;
        this.segments = segments;
        this.colors = colors;
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
        this.segments.add(segment);
    }

    public void removeSegment(Segment segment) {
        this.segments.remove(segment);
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segements) {
        this.segments = segements;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void addColor(Color color) {
        this.colors.add(color);
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public static TruckBuilder builder() {
        return new TruckBuilder();
    }

    public static class TruckBuilder extends Truck {

        /**
         * builder static class for Truck
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

        public TruckBuilder withRangeType(final RangeType rangeType) {
            setRange(rangeType);
            return this;
        }

        public TruckBuilder addOneSegment(final Segment segment) {
            addSegment(segment);
            return this;
        }

        public TruckBuilder addOneColor(final Color color) {
            addColor(color);
            return this;
        }

        public Truck build() {
            return new Truck(this.getModel(), this.getEnginePower(), this.getRange(), this.getFuel(),
                    this.getSegments(), this.getColors());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enginePower == null) ? 0 : enginePower.hashCode());
        result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((range == null) ? 0 : range.hashCode());
        result = prime * result + ((segments == null) ? 0 : segments.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Truck other = (Truck) obj;
        if (enginePower == null) {
            if (other.enginePower != null)
                return false;
        } else if (!enginePower.equals(other.enginePower))
            return false;
        if (fuel != other.fuel)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (range != other.range)
            return false;
        if (segments == null) {
            if (other.segments != null)
                return false;
        } else if (!segments.equals(other.segments))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Truck [enginePower=" + enginePower + ", fuel=" + fuel + ", id=" + id + ", model=" + model + ", range="
                + range + ", segments=" + segments + "]";
    }

}