package az.netx.heroes.component.criteria;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageSearchCriteria {
    private String receivedDateFrom;
    private String receivedDateTo;
    private String readDateFrom;
    private String readDateTo;
    private String status;
    private String email;
}
