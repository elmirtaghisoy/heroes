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
    1 - Post ve kateqoriyalarini birleshdir.
    2 - Soldier ve kateqoriyalrini birelshdir.
    3 - Post ve Esger paging
    4 - wars and rewards
    5 - Hansi kateqoriyada esger varsa o gorunsun menuda (buna parametr elave et)
    6 - Hansi type da post varsa o kateqoriya menuda gorunsun (buna parametr elave et)

    6 - Fayllari elave et.
    7 - Contact service
    ---------------------------------------------
    1 - ci gun security
    2 - liquibase
    ---------------------------------
    3 - ci gun front
}*/