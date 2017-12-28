package hr.fer.tel.rasus.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import hr.fer.tel.rasus.clients.BeerClient;
import hr.fer.tel.rasus.representations.Beer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class GoodBeerController {
    private final BeerClient beerClient;

    public GoodBeerController(BeerClient beerClient) {
        this.beerClient = beerClient;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/good-beers")
    public Collection<Beer> goodBeers() {
        return beerClient.readBeers()
                .getContent()
                .stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Ožujsko") &&
                !beer.getName().equals("Stella Artois") &&
                !beer.getName().equals("Budweiser");
    }

    // Fallback method used by Hystrix to return an empty array list in case beer-catalog service is offline
    public Collection<Beer> fallback() {
        return new ArrayList<>();
    }
}
