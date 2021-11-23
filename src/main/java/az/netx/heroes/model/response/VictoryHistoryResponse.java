package az.netx.heroes.model.response;

import lombok.Data;

@Data
public class VictoryHistoryResponse {
    private Long id;
    private String header;
    private String context;
    private String filePath;
}
