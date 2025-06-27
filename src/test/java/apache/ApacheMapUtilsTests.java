package apache;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApacheMapUtilsTests {

    @Test
    public void testEmptyIfNullWhenNull() {
        Map<String, String> map = null;
        Map<String, String> emptyMap = MapUtils.emptyIfNull(map);

        assertThat(emptyMap).isNotNull();
        assertThat(emptyMap).hasSize(0);
    }

    @Test
    public void testEmptyIfNullWhenNotNull() {
        Map<String, String> map = Map.of("key1", "value1", "key2", "value2");
        Map<String, String> actual = MapUtils.emptyIfNull(map);

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);
        assertThat(actual).containsEntry("key1", "value1");
    }
}
