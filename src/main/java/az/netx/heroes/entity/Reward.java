package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Table(name = "reward")
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
