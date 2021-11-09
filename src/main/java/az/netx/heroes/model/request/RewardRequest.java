package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RewardRequest {

    private Long id;

    @NotNull(message = "Mükafatı daxil edin")
    @NotBlank(message = "Mükafatı daxil edin")
    private String rewardName;

    private String status = "ACTIVE";

}
