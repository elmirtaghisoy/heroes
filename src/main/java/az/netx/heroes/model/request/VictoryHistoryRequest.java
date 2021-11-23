package az.netx.heroes.model.request;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VictoryHistoryRequest {
    private Long id;
    private String header;
    private String context;
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private String filePath;
}
