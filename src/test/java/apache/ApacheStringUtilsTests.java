package apache;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApacheStringUtilsTests {

    /**
     * StringUtils.equals
     * Compares two strings for equality, handling null values.
     */
    @Test
    void equalsTest() {
        String str1 = "test";
        String str2 = "test";
        String str3 = null;

        assertThat(StringUtils.equals(str1, str2)).isTrue();
        assertThat(StringUtils.equals(str1, str3)).isFalse();
    }

    /**
     * StringUtils.defaultIfEmpty
     * Returns the default value if the string is empty or null.
     */
    @Test
    void testNullStringDefaultToEmptyString() {
        String fieldName = null;
        String updatedField = StringUtils.defaultIfEmpty(fieldName, "");
        assertThat(updatedField)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void testDefaultString() {
        String actual = StringUtils.defaultString(null);
        String actualWithDefault = StringUtils.defaultString(null, "default");
        String actualWithValue = StringUtils.defaultString("hi", "default");

        assertThat(actual).isEqualTo("");
        assertThat(actualWithDefault).isEqualTo("default");
        assertThat(actualWithValue).isEqualTo("hi");
    }
}
