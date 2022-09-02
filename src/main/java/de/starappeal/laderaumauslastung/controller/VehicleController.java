package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.api.SearchRequest;
import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.response.VehicleResponse;
import de.starappeal.laderaumauslastung.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;
    private final VehicleModelAssembler assembler;

    @Autowired
    public VehicleController(VehicleService service, VehicleModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<VehicleResponse>> findAll() {
        List<Vehicle> vehicles = service.findAll();

        return assembler.toCollectionModel(vehicles);
    }


    @GetMapping("/{id}")
    public EntityModel<VehicleResponse> findById(@PathVariable Long id) {

        Vehicle vehicle = service.findById(id);

        return assembler.toModel(vehicle);
    }

    @GetMapping("/raw/{id}")
    public Vehicle findByIdRaw(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<VehicleResponse> create(@RequestBody Vehicle vehicle) {
        return assembler.toModel(service.create(vehicle));
    }

    @PostMapping("/bulkCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionModel<EntityModel<VehicleResponse>> bulkCreate(@RequestBody List<Vehicle> vehicles) {
        return assembler.toCollectionModel(
                service.bulkCreate(vehicles)
        );
    }

    @PutMapping("/{id}")
    public EntityModel<VehicleResponse> update(
            @RequestBody Vehicle vehicle,
            @PathVariable Long id
    ) {
        return assembler.toModel(service.update(vehicle, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/filter")
    public ResponseEntity<PagedModel<EntityModel<VehicleResponse>>> findByFilter(
            PagedResourcesAssembler<Vehicle> pagedResourcesAssembler,
            @RequestBody SearchRequest request) {

        Page<Vehicle> vehicles = service.findByFilter(request);

        PagedModel<EntityModel<VehicleResponse>> response = pagedResourcesAssembler.toModel(
                vehicles,
                assembler
        );

        return ResponseEntity.ok(response);
    }
}
