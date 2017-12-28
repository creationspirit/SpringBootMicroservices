package hr.fer.tel.rasus.initializers;

import hr.fer.tel.rasus.entities.Beer;
import hr.fer.tel.rasus.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class BeerInitializer implements CommandLineRunner {
    private final BeerRepository beerRepository;

    BeerInitializer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Beer beer = new Beer("Karlovačko", 5.5);
        beerRepository.save(beer);

        beer = new Beer("Ožujsko", 4.5);
        beerRepository.save(beer);

        beer = new Beer("Velebitsko", 5.6);
        beerRepository.save(beer);

        beer = new Beer("Nikšićko", 5.7);
        beerRepository.save(beer);

        beer = new Beer("Tomislav", 7.0);
        beerRepository.save(beer);

        beer = new Beer("Budweiser", 4.6);
        beerRepository.save(beer);

        beer = new Beer("Heineken", 5.1);
        beerRepository.save(beer);

        beer = new Beer("Stella Artois", 4.8);
        beerRepository.save(beer);

        beerRepository.findAll().forEach(System.out::println);
    }
}
