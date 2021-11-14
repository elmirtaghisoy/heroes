package az.netx.heroes.model.response;

import az.netx.heroes.component.annotation.IsImage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@Data
public class MartyredResponse {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private LocalDate birthDate;
    private String about;
    @Transient
    @IsImage(message = "Əlavə etdiyiniz faylın formatı ancaq (JPG, JPEG, IMG, PNG) ola bilər.")
    private MultipartFile img;
    private String filePath;
    private LocalDate wasMartyred;
    private RankResponse rank;
    private List<RewardResponse> rewards;
    private List<WarResponse> wars;
}
