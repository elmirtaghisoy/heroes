package az.netx.heroes.model.response;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;

@Data
public class PostResponse {
    private Long id;
    private String header;
    private String context;
    private PostCategoryResponse category;
    private String filePath;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
}
