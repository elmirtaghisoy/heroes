package az.netx.heroes.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponse {
    private Long id;
    private String email;
    private String context;
    private String header;
    private String status;
    private LocalDateTime receivedTs;
    private LocalDateTime readTs;
}
