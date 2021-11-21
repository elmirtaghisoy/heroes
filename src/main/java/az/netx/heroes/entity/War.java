package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class War extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Döyüşün adını daxil edin")
    @NotBlank(message = "Döyüşün adını daxil edin")
    @Column(name = "war_name")
    private String warName;

    @ManyToMany(mappedBy = "wars", fetch = FetchType.EAGER)
    private List<Soldier> soldiers;

}
