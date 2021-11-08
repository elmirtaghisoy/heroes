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
    ---------------------------------
    1 - kenanin kodlarin birleshdir
    2 - post kateqoriyasinin gorunub gorunmemesine bax
    3 - post ve kateqoriyalarini birleshdir.
    4 - fayllari elave et.
    5 - paging'i duzelt.
------------------------------------------------
    1 - Hansi kateqoriyada esger varsa o gorunsun menuda
    2 - esger crud + search
    3 - post ve esgerlerde paging
    ---------------------------------
    2 - ci gun security
    ---------------------------------
    3 - ci gun front
}*/