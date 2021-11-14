package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.service.HeroService;
import az.netx.heroes.service.MartyredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    MartyredService martyredService;
    @Autowired
    HeroService heroService;

    @GetMapping("/ping")
    public MartyredResponse ping(
            @RequestParam("id") Long id
    ) {
        return martyredService.getMartyredById(id);
    }


    @GetMapping("/search")
    public Paged<HeroResponse> search(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "birthDate", required = false) String birthDate,
            @RequestParam(value = "fatherName", required = false) String fatherName,
            @RequestParam(value = "rank", required = false) Long rankId,
            @RequestParam(value = "wars", required = false) List<Long> warsIds
    ) {
        var criteria = new HeroSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setBirthDate(birthDate);
        criteria.setRankId(rankId);
        criteria.setWars(List.of(3L));

        Paged<HeroResponse> list = heroService.searchHero(
                page,
                size,
                criteria
        );

        return list;
    }
}
