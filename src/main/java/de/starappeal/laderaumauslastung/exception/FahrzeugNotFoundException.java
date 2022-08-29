package de.starappeal.laderaumauslastung.exception;


public class FahrzeugNotFoundException extends RuntimeException {

    public FahrzeugNotFoundException() {
        super();
    }

    public FahrzeugNotFoundException(String message) {
        super(message);
    }

    public FahrzeugNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
