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
    1 - default bashliqli fayllar
    ---------------------------------------------
    3 - Message servisi yarat.
    4 - Pageingde link meseleleri
    5 - liquibase
    6 - List ile search
    7 - optimization
    8 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    9 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    10 - ci gun security
    ---------------------------------------------
    Front
}
*/