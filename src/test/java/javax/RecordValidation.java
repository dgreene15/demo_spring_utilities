package javax;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordValidation {

    @Test
    public void validatePersonValid() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        PersonValidation validPerson = new PersonValidation("John Doe");

        Set<ConstraintViolation<PersonValidation>> violations = validator.validate(validPerson);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validatePersonInValid() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        PersonValidation invalidPerson = new PersonValidation("John123");

        Set<ConstraintViolation<PersonValidation>> violations = validator.validate(invalidPerson);

        assertThat(invalidPerson).isNotNull();
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid name. Only letters and spaces are allowed.");
    }

    record PersonValidation(
            @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Invalid name. Only letters and spaces are allowed.")
            String name
    ) {}
}
