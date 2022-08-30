package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.exception.VehicleAlreadyExistsException;
import de.starappeal.laderaumauslastung.exception.VehicleNotFoundException;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class VehicleControllerAdvice {

    @ResponseBody
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> vehicleNotFoundHandler(VehicleNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Not found")
                        .withDetail(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<?> vehicleAlreadyExistsHandler(VehicleAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail(exception.getMessage()));
    }
}
