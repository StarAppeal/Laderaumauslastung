package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.api.SearchRequest;
import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.model.VehicleModel;
import de.starappeal.laderaumauslastung.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<VehicleModel> findAll() {
        return service
                .findAll()
                .stream()
                .map(VehicleModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VehicleModel findById(@PathVariable Long id) {
        return new VehicleModel(service.findById(id));
    }

    @PostMapping("/vehicles")
    public VehicleModel create(@RequestBody Vehicle vehicle) {
        return new VehicleModel(service.create(vehicle));
    }

    @PostMapping("/bulkCreate")
    public List<VehicleModel> bulkCreate(@RequestBody List<Vehicle> vehicles) {
        return service
                .bulkCreate(vehicles)
                .stream()
                .map(VehicleModel::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/")
    public VehicleModel update(@RequestBody Vehicle vehicle) {
        return new VehicleModel(service.update(vehicle));
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteById(@PathVariable Long id) {
        service.delete(id);
        return "OK"; //TODO: change this return type
    }


    @PostMapping("/filter")
    public Page<VehicleModel> findByFilter(@RequestBody SearchRequest request) {
        return service.findByFilter(request).map(VehicleModel::new);
    }
}
