package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Contact extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Ünvanı daxil edin")
    @Column(name = "contact_address")
    private String contactAddress;

    @NotBlank(message = "")
    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "icon")
    private String icon;
}
