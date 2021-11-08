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
    1 - esger crud + search
    2 - Hansi kateqoriyada esger varsa o gorunsun menuda
    ---------------------------------------------
    3 - fayllari elave et.
    4 - pagingler'i duzelt.
    ---------------------------------------------
    1 - kenanin kodlarin birleshdir
    2 - post ve kateqoriyalarini birleshdir.
    3 - post kateqoriyasinin gorunub gorunmemesine bax
    4 - post ve esgerlerde paging
    ---------------------------------------------



    2 - ci gun security
    ---------------------------------
    3 - ci gun front
}*/