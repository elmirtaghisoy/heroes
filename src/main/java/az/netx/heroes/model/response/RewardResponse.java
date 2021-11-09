package az.netx.heroes.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardResponse {
    private Long id;
    private String rewardName;
    private String filePath;
}
