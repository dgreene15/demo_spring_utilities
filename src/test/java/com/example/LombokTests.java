package com.example;

import lombok.Builder;
import lombok.SneakyThrows;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LombokTests {

    /**
     * @SneakyThrows
     * - Allows throwing an exception but not have to catch or declare it
     */
    @SneakyThrows
    public void testSneakyThrows() {
        throw new IOException("IO Exception");
    }

    @Test
    public void testBuilders() {
        // Create an instance of Person
        Person person = Person.builder()
                .name("John Doe")
                .age(30)
                .address("123 Main St")
                .build();

        System.out.println(person); // Output: Person(name=John Doe, age=30, address=123 Main St)

        // Create a new instance based on the existing one, with some modifications
        Person updatedPerson = person.toBuilder()
                .age(31)
                .address("456 Elm St")
                .build();

        System.out.println(updatedPerson); // Output: Person(name=John Doe, age=31, address=456 Elm St)
    }
}


@Builder(toBuilder = true)
@ToString
class Person {
    private final String name;
    private final int age;
    private final String address;
}