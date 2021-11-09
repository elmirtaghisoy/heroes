package az.netx.heroes.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 4, discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "soldier_info_map",
            joinColumns = @JoinColumn(name = "soldier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id", referencedColumnName = "id")
    )
    private List<Reward> rewards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "soldier_info_map",
            joinColumns = @JoinColumn(name = "soldier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "war_id", referencedColumnName = "id")
    )
    private List<War> wars;

}
