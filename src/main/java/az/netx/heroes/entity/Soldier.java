package az.netx.heroes.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 4, discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@ToString
public abstract class Soldier extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "about")
    private String about;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "type", insertable = false, updatable = false)
    @Setter(value = AccessLevel.NONE)
    private String type;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "soldier_reward",
            joinColumns = @JoinColumn(name = "soldier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id", referencedColumnName = "id")
    )
    private List<Reward> rewards;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "soldier_war",
            joinColumns = @JoinColumn(name = "soldier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "war_id", referencedColumnName = "id")
    )
    private List<War> wars;

}
