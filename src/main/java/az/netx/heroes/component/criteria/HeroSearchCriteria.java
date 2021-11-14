package az.netx.heroes.component.criteria;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class HeroSearchCriteria {
    private String name;
    private String surname;
    private String fatherName;
    private String birthDate;
    private Long rankId;
    private List<Long> wars;
    private List<Long> rewards;
}
