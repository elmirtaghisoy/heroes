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
    1 - Fayl upload error (global errors)
    3 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    4 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    5 - CodeRefactoring
    6 - Liquibase
    ---------------------------------------------
    1 - Admin panel kontekstinin duzeldilmesi
    2 - Admin panel dizayninda deyishiklikler
    4 - Message servisi (mesajlarin oxunmasi prosesi dizanyi ajax)
    5 - 1st level security
    6 - List ile search
    ---------------------------------------------
    Front
}
*/