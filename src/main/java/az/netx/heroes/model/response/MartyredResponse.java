package az.netx.heroes.model.response;

import lombok.Data;

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
    private String filePath;
    private RankResponse rank;
    private List<RewardResponse> rewards;
    private List<WarResponse> wars;
}
