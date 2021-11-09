package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Table(name = "ranks")
@Entity
public class Rank extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Vəzifə adını daxil edin")
    @NotBlank(message = "Vəzifə adını daxil edin")
    @Column(name = "rank_name")
    private String rankName;

    @OneToMany(mappedBy = "rank")
    private List<Soldier> soldiers;
}
