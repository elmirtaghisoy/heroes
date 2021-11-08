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
    Optimization
    post search +
    post create +
    post update +
    post delete +
    post paging -
    ---------------------------------
    1 - ci gun fayllar
    // postcategory changes (post kateqoriyasi menuda gorunsun ya yox)

    // hansi kateqoriyada esger varsa o gorunsun menuda
    1 - ci gun axsham esger meseleleri
    ---------------------------------
    2 - ci gun security
    ---------------------------------
    3 - ci gun front
}*/

// standard model, dto, mapping, services
// search
//---------------------------
// exception handling
// logging

// ui