package com.example;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LombokTests {

    /**
     * @SneakyThrows
     * - Allows throwing an exception but not have to catch or declare it
     */
    @SneakyThrows
    @Test
    public void testSneakyThrows() {
        boolean exceptionThrown = false;
        if(exceptionThrown) {
            throw new IOException("IO Exception");
        }
    }

    @Test
    public void testBuilders() {
        // Create an instance of Person
        Person person = Person.builder()
                .name("John Doe")
                .age(30)
                .address("123 Main St")
                .build();

        assertThat(person.getName()).isEqualTo("John Doe");
        assertThat(person.getAge()).isEqualTo(30);
        assertThat(person.getAddress()).isEqualTo("123 Main St");

        // Create a new instance based on the existing one, with some modifications
        Person updatedPerson = person.toBuilder()
                .age(31)
                .address("456 Elm St")
                .build();

        assertThat(updatedPerson.getName()).isEqualTo("John Doe");
        assertThat(updatedPerson.getAge()).isEqualTo(31);
        assertThat(updatedPerson.getAddress()).isEqualTo("456 Elm St");
    }
}

@Data
@Builder(toBuilder = true)
@ToString
class Person {
    private final String name;
    private final int age;
    private final String address;
}