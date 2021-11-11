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
    ---------------------------------------------
    1 - Soldier CSUD (front)
    ---------------------------------------------
    2 - Post ve kateqoriyalarini birleshdir.
    3 - Soldier ve kateqoriyalrini birelshdir.
    4 - Post ve Esger paging
    5 - Hansi kateqoriyada esger varsa o gorunsun menuda
    6 - Hansi type da post varsa o kateqoriya menuda gorunsun

    7 - Fayllari elave et.
    8 - Contact service
    ---------------------------------------------
    1 - ci gun security
    2 - liquibase
    ---------------------------------
    3 - ci gun front
}*/