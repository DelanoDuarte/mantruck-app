package com.man.truckapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_segment")
public class Segment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public Segment(String description) {
        this.description = description;
    }

    public Segment(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Segment() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static SegmentBuilder builder() {
        return new SegmentBuilder();
    }

    public static class SegmentBuilder extends Segment {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public SegmentBuilder withDescription(final String description) {
            setDescription(description);
            return this;
        }

        public Segment build() {
            return new Segment(this.getDescription());
        }
    }
}
