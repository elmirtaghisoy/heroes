package az.netx.heroes.model.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class MartyredRequest {
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
    private String filePath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate wasMartyred;
    private RankRequest rank;
    private List<RewardRequest> rewards;
    private List<WarRequest> wars;
}
