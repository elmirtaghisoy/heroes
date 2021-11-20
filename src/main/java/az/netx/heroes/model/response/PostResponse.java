package az.netx.heroes.model.response;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private String header;
    private String context;
    private PostCategoryResponse category;
    private String filePath;
    private LocalDateTime createdDate;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private List<FileResponse> filePaths;
}
