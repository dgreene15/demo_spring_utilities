package apache;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTests {

    @Test
    void testNullStringDefaultToEmptyString() {
        String fieldName = null;
        String updatedField = StringUtils.defaultIfEmpty(fieldName, "");
        Assertions.assertThat(updatedField)
                .isNotNull()
                .isEmpty();
    }
}
