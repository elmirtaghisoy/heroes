package az.netx.heroes.model.response;

import lombok.Data;

@Data
public class FileResponse {
    private Long id;
    private int fileType;
    private String folder;
    private String category;
    private Long refObjectId;
}
