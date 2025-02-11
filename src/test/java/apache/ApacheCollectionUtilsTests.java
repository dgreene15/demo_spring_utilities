package apache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApacheCollectionUtilsTests {
    /*
    * CollectionUtils.isEmpty
    * Safe was to check if collection is empty or null
    * Avoid have to have a && check to first check if null
    *
     */
    @Test
    void testIsEmpty() {
        List<String> list = new ArrayList<>();
        List<String> list2 = null;
        List<String> list3 = new ArrayList<>();
        list3.add("test");

        assertThat(CollectionUtils.isEmpty(list)).isTrue();
        assertThat(CollectionUtils.isEmpty(list2)).isTrue();
        assertThat(CollectionUtils.isEmpty(list3)).isFalse();
    }
}
