package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
