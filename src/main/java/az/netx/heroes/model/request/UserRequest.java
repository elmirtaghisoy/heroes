package az.netx.heroes.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserRequest {
    private Long id;
    @NotNull(message = "İstifadəçi adını daxil edin")
    @NotBlank(message = "İstifadəçi adını daxil edin")
    private String username;
    private String email;
    @NotNull(message = "Şifrəni daxil edin")
    @NotBlank(message = "Şifrəni daxil edin")
    private String password1;
    @NotNull(message = "Şifrəni təsdiq edin")
    @NotBlank(message = "Şifrəni təsdiq edin")
    private String password2;
    @NotNull(message = "Adı daxil edin")
    @NotBlank(message = "Adı daxil edin")
    private String name;
    @NotNull(message = "Soyadın daxil edin")
    @NotBlank(message = "Soyadın daxil edin")
    private String surname;
    private String status;
}
