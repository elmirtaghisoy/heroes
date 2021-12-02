package az.netx.heroes.component.criteria;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MartyredSearchCriteria {
    private String name;
    private String surname;
    private String fatherName;
    private Long rankId;
    private Long warId;
    private Long rewardId;
}
