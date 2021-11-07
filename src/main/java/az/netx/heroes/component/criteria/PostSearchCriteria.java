package az.netx.heroes.component.criteria;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostSearchCriteria {
    private String fromDate;
    private String toDate;
    private String header;
    private Long categoryId;
}
