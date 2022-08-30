package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.api.SearchRequest;
import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.response.VehicleResponse;
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
    public List<VehicleResponse> findAll() {
        return service
                .findAll()
                .stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VehicleResponse findById(@PathVariable Long id) {
        return new VehicleResponse(service.findById(id));
    }

    @PostMapping("/")
    public VehicleResponse create(@RequestBody Vehicle vehicle) {
        return new VehicleResponse(service.create(vehicle));
    }

    @PostMapping("/bulkCreate")
    public List<VehicleResponse> bulkCreate(@RequestBody List<Vehicle> vehicles) {
        return service
                .bulkCreate(vehicles)
                .stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/")
    public VehicleResponse update(@RequestBody Vehicle vehicle) {
        return new VehicleResponse(service.update(vehicle));
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteById(@PathVariable Long id) {
        service.delete(id);
        return "OK"; //TODO: change this return type
    }


    @PostMapping("/filter")
    public Page<VehicleResponse> findByFilter(@RequestBody SearchRequest request) {
        return service.findByFilter(request).map(VehicleResponse::new);
    }
}
