package az.netx.heroes.component.criteria;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HeroSearchCriteria {
    private String name;
    private String surname;
    private String fatherName;
    private String birthDate;
    private Long rankId;
}
