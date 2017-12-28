package hr.fer.tel.rasus.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import hr.fer.tel.rasus.clients.BeerClient;
import hr.fer.tel.rasus.representations.Beer;
import hr.fer.tel.rasus.representations.ShortBeerRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class StrongBeerController {
    private static final Double STRONG_BEER_ALCOHOL_VOL_THRESHOLD = 5.0;
    private final BeerClient beerClient;

    public StrongBeerController(BeerClient beerClient) {
        this.beerClient = beerClient;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/strong-beers")
    public Collection<ShortBeerRepresentation> strongBeers() {
        return beerClient.readBeers()
                .getContent()
                .stream()
                .filter(this::isStrong)
                .map((beer) -> new ShortBeerRepresentation(beer.getName()))
                .collect(Collectors.toList());
    }

    private boolean isStrong(Beer beer) {
        return beer.getAlcoholVol() > STRONG_BEER_ALCOHOL_VOL_THRESHOLD;
    }

    // Fallback method used by Hystrix to return an empty array list in case beer-catalog service is offline
    public Collection<ShortBeerRepresentation> fallback() {
        return new ArrayList<>();
    }

}
