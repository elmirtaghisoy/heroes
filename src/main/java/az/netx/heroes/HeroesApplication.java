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
    2 - Fayl upload error (global errors)
    3 - Message servisi (mesajlarin oxunmasi prosesi dizanyi ajax)
    4 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    5 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    6 - CodeRefactoring
    7 - Liquibase
    ---------------------------------------------
    1 - Admin panel kontekstinin duzeldilmesi
    2 - Admin panel dizayninda deyishiklikler
    3 - 1st level security
    4 - List ile search
    ---------------------------------------------
    Front
}
*/