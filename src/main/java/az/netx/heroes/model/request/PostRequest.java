package az.netx.heroes.model.request;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostRequest {
    private Long id;
    @NotNull(message = "Paylaşımın başlığını daxil edin")
    @NotBlank(message = "Paylaşımın başlığını daxil edin")
    private String header;
    @NotNull(message = "Paylaşımın içəriyini daxil edin")
    @NotBlank(message = "Paylaşımın içəriyini daxil edin")
    private String context;
    private PostCategoryRequest category;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private String filePath;
}
