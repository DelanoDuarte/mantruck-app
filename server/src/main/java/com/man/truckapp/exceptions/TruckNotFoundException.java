package com.man.truckapp.exceptions;

public class TruckNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    TruckNotFoundException(Long id) {
        super("could not find a truck with the id: " + id);
    }

    TruckNotFoundException(String message) {
        super(message);
    }
}