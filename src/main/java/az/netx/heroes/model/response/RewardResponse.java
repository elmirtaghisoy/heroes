package az.netx.heroes.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RewardResponse {
    private Long id;
    private String rewardName;
    private String filePath;
}
