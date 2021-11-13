package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class WarRequest {

    private Long id;

    @NotNull(message = "Döyüşün adını daxil edin")
    @NotBlank(message = "Döyüşün adını daxil edin")
    private String warName;

    private String status = "ACTIVE";

}
