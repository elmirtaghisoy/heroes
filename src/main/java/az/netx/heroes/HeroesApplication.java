package az.netx.heroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesApplication.class, args);
    }

}

/*{
    1 - Fayllari elave et.
    2 - Message servisi yarat.
    3 - Pageingde link meseleleri
    4 - List ile search
    5 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    6 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    ---------------------------------------------
    1 - ci gun security
    2 - liquibase
    ---------------------------------
    Front
}*/