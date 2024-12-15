import lombok.Data;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct
 * Tool for object to object mapping
 */
public class MapStructTests {

    @Test
    public void basicMapStructTest() {
        Source source = new Source();
        source.setName("John Doe");
        source.setAge(30);

        Target target = SourceTargetMapper.INSTANCE.sourceToTarget(source);

        System.out.println("Full Name: " + target.getFullName());
        System.out.println("Years: " + target.getYears());
    }

}


@Mapper
interface SourceTargetMapper {
    SourceTargetMapper INSTANCE = Mappers.getMapper(SourceTargetMapper.class);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "age", target = "years")
    Target sourceToTarget(Source source);

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "years", target = "age")
    Source targetToSource(Target target);
}

@Data
class Source {
    private String name;
    private int age;
}

@Data
class Target {
    private String fullName;
    private int years;
}