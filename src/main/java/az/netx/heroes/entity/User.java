package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "status", insertable = false)
    private String status;

    @Column(name = "is_enable", insertable = false, columnDefinition = "boolean default 1")
    private Integer isEnable;

    @Column(name = "is_admin", insertable = false, columnDefinition = "boolean default 1")
    private Integer isAdmin;
}
