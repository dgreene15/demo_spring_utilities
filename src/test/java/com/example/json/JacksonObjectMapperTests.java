package com.example.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonObjectMapperTests {
    @Test
    void writeValueAsString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "truck");

        assertThat(objectMapper.writeValueAsString(car)).isEqualTo("{\"color\":\"yellow\",\"type\":\"truck\"}");
        assertThat(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car)).isEqualTo("{\n  \"color\" : \"yellow\",\n  \"type\" : \"truck\"\n}");
    }

    @Test
    void readValueJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"color\":\"yellow\",\"type\":\"truck\"}";
        Car car = objectMapper.readValue(json, Car.class);

        assertThat(car.getColor()).isEqualTo("yellow");
    }

    @Test
    void readTreeJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"color\":\"yellow\",\"type\":\"truck\"}";
        JsonNode rootNode = objectMapper.readTree(json);
        String color = rootNode.get("color").asText();
        String type = rootNode.get("type").asText();

        assertThat(color).isEqualTo("yellow");
        assertThat(type).isEqualTo("truck");
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {

    private String color;
    private String type;
}
