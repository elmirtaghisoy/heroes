package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Reward extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Mükafatı daxil edin")
    @NotBlank(message = "Mükafatı daxil edin")
    @Column(name = "reward_name")
    private String rewardName;

    @Column(name = "file_path")
    private String filePath;

    @ManyToMany(mappedBy = "rewards")
    private List<Soldier> soldiers;
}
