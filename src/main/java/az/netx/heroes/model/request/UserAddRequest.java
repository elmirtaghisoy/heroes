package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserAddRequest {
    private Long id;
    @NotNull(message = "İstifadəçi adını daxil edin")
    @NotBlank(message = "İstifadəçi adını daxil edin")
    private String username;
    private String email;
    @NotNull(message = "Adı daxil edin")
    @NotBlank(message = "Adı daxil edin")
    private String name;
    @NotNull(message = "Soyadın daxil edin")
    @NotBlank(message = "Soyadın daxil edin")
    private String surname;
    private Integer isAdmin;
}
