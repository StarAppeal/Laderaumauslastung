package de.starappeal.laderaumauslastung.exception;

public class VehicleCreateException extends RuntimeException {

    public VehicleCreateException() {
        super();
    }

    public VehicleCreateException(String message) {
        super(message);
    }

    public VehicleCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
