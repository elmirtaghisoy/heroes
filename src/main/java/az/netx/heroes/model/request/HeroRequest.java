package az.netx.heroes.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HeroRequest {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private LocalDate birthDate;
    private String about;
    private String filePath;
    private RankRequest rank;
    private List<RewardRequest> rewards;
    private List<WarRequest> wars;
}
