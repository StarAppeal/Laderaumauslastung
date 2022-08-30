package de.starappeal.laderaumauslastung.controller;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.response.VehicleResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VehicleModelAssembler implements RepresentationModelAssembler<Vehicle, EntityModel<VehicleResponse>> {

    @Override
    public EntityModel<VehicleResponse> toModel(Vehicle entity) {
        return EntityModel.of(new VehicleResponse(entity),
                linkTo(methodOn(VehicleController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(VehicleController.class).findAll()).withRel("vehicles")
        );
    }

}
