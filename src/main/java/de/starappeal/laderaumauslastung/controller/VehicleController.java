package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.model.VehicleModel;
import de.starappeal.laderaumauslastung.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    private VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/vehicles")
    public List<VehicleModel> findAll() {
        return service.findAll().stream().map(VehicleModel::new).collect(Collectors.toList());
    }

}
