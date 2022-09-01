package de.starappeal.laderaumauslastung.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.starappeal.laderaumauslastung.db.entity.Storage;
import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllVehicles() throws Exception {
        mockMvc.perform(
                        get("/vehicles/")
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList", hasSize(3))
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList[0].vehicleId").value("FAHRZEUGKENNUNG 1")
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList[1].vehicleId").value("FAHRZEUGKENNUNG 2")
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList[2].vehicleId").value("FAHRZEUGKENNUNG 3")
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList[0]._links.self").exists()
                ).andExpect(
                        jsonPath("$._embedded.vehicleResponseList[0]._links.vehicles.href").value("http://localhost/vehicles/")
                );
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void shouldReturnSingleVehicleWithGivenId(Long id) throws Exception {
        mockMvc.perform(
                        get("/vehicles/" + id)
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isOk()
                ).andExpect(
                        jsonPath("$.vehicleId").value("FAHRZEUGKENNUNG " + id)
                ).andExpect(
                        jsonPath("$._links.self.href").value("http://localhost/vehicles/" + id)
                ).andExpect(
                        jsonPath("$._links.vehicles.href").value("http://localhost/vehicles/")
                );
    }

    @Test
    void shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(
                        get("/vehicles/1234")
                )
                .andDo(
                        print()
                )
                .andExpect(
                        status().isNotFound()
                )
                .andExpect(
                        header().stringValues(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                ).andExpect(
                        jsonPath("$.title").value("Not found")
                ).andExpect(
                        jsonPath("$.detail").value("Vehicle with ID 1234 not found!")
                );
    }

    @Test
    void shouldCreateNewVehicle() throws Exception {
        Vehicle vehicle = new Vehicle(null, "VehicleID 4", 3, 5, new Storage(null, 5d, 2d, 3d), 3.5, false);

        mockMvc.perform(
                post("/vehicles/").content(
                        toJson(vehicle)
                ).contentType(MediaType.APPLICATION_JSON)
        ).andDo(
                print()
        ).andExpect(
                status().isCreated()
        ).andExpect(
                jsonPath("$.vehicleId").value(vehicle.getVehicleId())
        );
    }

    @Test
    void shouldReturnMethodNotAllowed() throws Exception {
        Vehicle vehicle = new Vehicle(5L, "VehicleID 4", 3, 5, new Storage(null, 5d, 2d, 3d), 3.5, false);

        mockMvc.perform(
                post("/vehicles/").content(
                        toJson(vehicle)
                ).contentType(MediaType.APPLICATION_JSON)
        ).andDo(
                print()
        ).andExpect(
                status().isMethodNotAllowed()
        ).andExpect(
                header().stringValues(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        ).andExpect(
                jsonPath("$.title").value("Method not allowed")
        ).andExpect(
                jsonPath("$.detail").value("Vehicle should not have an id to create it")
        );
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
