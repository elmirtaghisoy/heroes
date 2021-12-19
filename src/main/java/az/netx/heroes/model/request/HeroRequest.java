package az.netx.heroes.model.request;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class HeroRequest {
    private Long id;
    @NotNull(message = "Adı daxil edin.")
    @NotBlank(message = "Adı daxil edin.")
    private String name;
    @NotNull(message = "Soyadı daxil edin.")
    @NotBlank(message = "Soyadı daxil edin.")
    private String surname;
    @NotNull(message = "Ata adını daxil edin.")
    @NotBlank(message = "Ata adını daxil edin.")
    private String fatherName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String about;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private String filePath;
    @NotNull(message = "Rütbəni daxil edin.")
    private RankRequest rank;
    private List<RewardRequest> rewards;
    private List<WarRequest> wars;
}
