package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@DiscriminatorValue(value = "M")
public class Martyred extends Soldier {

    @Column(name = "was_martyred")
    private LocalDate wasMartyred;

}
