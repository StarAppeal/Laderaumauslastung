package de.starappeal.laderaumauslastung.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SearchRequest(List<FilterRequest> filters, List<SortRequest> sorts, Integer page,
                            Integer size) implements Serializable {

    @Serial
    private static final long serialVersionUID = -5086439760355052742L;

}
