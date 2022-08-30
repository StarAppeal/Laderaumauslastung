package de.starappeal.laderaumauslastung.controller.advice;

import de.starappeal.laderaumauslastung.exception.VehicleAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VehicleAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(VehicleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String vehicleAlreadyExistsHandler(VehicleAlreadyExistsException exception) {
        return exception.getMessage();
    }
}
