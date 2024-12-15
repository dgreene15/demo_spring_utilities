package com.example.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class JacksonObjectMapperTests {
    @Test
    @Disabled
    void testWriteValueAsString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "truck");
        System.out.println("String: " + objectMapper.writeValueAsString(car));
        System.out.println("Pretty String: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car));
    }

    @Test
    @Disabled
    void testReadJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"color\":\"yellow\",\"type\":\"truck\"}";
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println(car.getColor() + " : " + car.getType());

        JsonNode rootNode = objectMapper.readTree(json);
        String color = rootNode.get("color").asText();
        String type = rootNode.get("type").asText();
        System.out.println(color + " : " + type);

    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {

    private String color;
    private String type;
}
