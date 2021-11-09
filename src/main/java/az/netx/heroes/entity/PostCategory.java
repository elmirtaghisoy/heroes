package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class PostCategory extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Kateqoriyanı daxil edin")
    @NotBlank(message = "Kateqoriyanı daxil edin")
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "visibility")
    private Boolean isVisible;

    @OneToMany(mappedBy = "category")
    private List<Post> soldiers;
}
