package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @Column(name = "reward_name")
    private String rewardName;

    @Column(name = "file_path")
    private String filePath;

    @ManyToMany(mappedBy = "rewards")
    private List<Soldier> soldiers;
}
