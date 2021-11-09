package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Table(name = "war")
@Entity
@ToString(callSuper = true)
public class War extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Döyüşün adını daxil edin")
    @NotBlank(message = "Döyüşün adını daxil edin")
    @Column(name = "war_name")
    private String warName;

    @ManyToMany(mappedBy = "wars")
    @ToString.Exclude
    private List<Soldier> soldiers;

}
