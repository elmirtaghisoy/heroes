package az.netx.heroes.controller;

import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.service.MartyredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    MartyredService martyredService;

    @GetMapping("/ping")
    public MartyredResponse ping(
            @RequestParam("id") Long id
    ) {
        return martyredService.getMartyredById(id);
    }
}
