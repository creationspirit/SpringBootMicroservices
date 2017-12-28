package hr.fer.tel.rasus.clients;

import hr.fer.tel.rasus.representations.Beer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("beer-catalog-service")
public interface BeerClient {

    @GetMapping("/beers")
    Resources<Beer> readBeers();
}
