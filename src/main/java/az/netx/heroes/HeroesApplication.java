package az.netx.heroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesApplication.class, args);
    }

}

/*
{

    ---------------------------------------------
    1 + 1st level security
    2 + 2nd level security
    2 - Liquibase
    ---------------------------------------------
    Front
    ---------------------------------------------
    1 - Post Ve Solider'in menuda gorunub gorunmemesi
    2 - Admin panel kontekstinin duzeldilmesi
    3 - Message servisi (mesajlarin oxunmasi prosesi dizanyi ajax)
    4 - Deleteden sonra son linke redirect olsun
    5 - List ile search ?
    6 - Admin panel dizayninda deyishiklikler
}
*/