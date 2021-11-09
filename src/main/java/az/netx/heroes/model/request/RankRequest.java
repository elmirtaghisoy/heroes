package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RankRequest {

    private Long id;

    @NotNull(message = "Rütbəni daxil edin")
    @NotBlank(message = "Rütbəni daxil edin")
    private String rankName;

    private String status = "ACTIVE";
}
