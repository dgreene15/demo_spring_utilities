import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        log.info("list (empty) Status: " + CollectionUtils.isEmpty(list));
        log.info("list2 (null) Status: " + CollectionUtils.isEmpty(list2));
        log.info("list3 (has one element) Status: " + CollectionUtils.isEmpty(list3));
    }
}
