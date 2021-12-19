package az.netx.heroes.model.request;

import az.netx.heroes.component.annotation.IsImage;
import az.netx.heroes.entity.File;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PostRequest {
    private Long id;
    @NotNull(message = "Paylaşımın başlığını daxil edin")
    @NotBlank(message = "Paylaşımın başlığını daxil edin")
    private String header;
    @NotNull(message = "Paylaşımın içəriyini daxil edin")
    @NotBlank(message = "Paylaşımın içəriyini daxil edin")
    private String context;
    @NotNull(message = "Paylaşımın kateqoriyasını daxil edin.")
    private PostCategoryRequest category;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private String filePath;
    private List<File> filePaths;
    private String folderUuid;
    @Transient
    private List<MultipartFile> files;
}
