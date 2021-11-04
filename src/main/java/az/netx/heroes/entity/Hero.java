package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
@DiscriminatorValue(value = "H")
public class Hero extends Soldier {

}
