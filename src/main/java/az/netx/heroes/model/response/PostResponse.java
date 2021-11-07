package az.netx.heroes.model.response;

import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String header;
    private String context;
}
