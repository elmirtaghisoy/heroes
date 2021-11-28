package az.netx.heroes.model.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Long id;
    private String email;
    private String context;
    private String header;
}
