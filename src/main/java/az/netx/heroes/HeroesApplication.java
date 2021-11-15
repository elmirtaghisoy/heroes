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
    1 - Pageingde link meseleleri
    2 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    3 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    4 - CodeRefactoring
    5 - Liquibase
    6 - List ile search
    ---------------------------------------------
    1 - Admin panel kontekstinin duzeldilmesi
    2 - Success responslarda bug var
    3 - Message servisi (mesajlarin oxunmasi prosesi dizanyi ajax)
    4 - Deleti ajaxla etmekde problemler var
    5 - Admin panel dizayninda deyishiklikler
    7 - Fayl upload error (global errors)
    ---------------------------------------------
    10 - ci gun security
    ---------------------------------------------
    Front
}
*/