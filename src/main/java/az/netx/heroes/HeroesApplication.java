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
    1 - Message servisi yarat.
    2 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    3 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    4 - Pageingde link meseleleri
    ---------------------------------------------
    5 - optimization
    6 - liquibase
    7 - List ile search
    ---------------------------------------------
    10 - ci gun security
    ---------------------------------------------
    Front
}
*/