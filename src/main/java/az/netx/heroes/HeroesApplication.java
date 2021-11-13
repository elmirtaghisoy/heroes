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
    1 - Pageingde link meseleleri
    2 - List ile search
    3 - Fayllari elave et.
    4 - Message servisi birleshdir.
    5 - Contact service
    ---------------------------------------------
    3 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    4 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)
    ---------------------------------------------
    1 - ci gun security
    2 - liquibase
    ---------------------------------
    Front
}*/