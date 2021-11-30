package az.netx.heroes.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String name;
    private String surname;
    private String status;
    private Integer isEnable;
    private Integer isAdmin;
}
