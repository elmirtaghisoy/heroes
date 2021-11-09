package az.netx.heroes.model.response;

import lombok.Data;

@Data
public class RewardResponse {
    private Long id;
    private String rewardName;
    private String filePath;
}
