package se.anna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.anna.business.Player;
import se.anna.business.PlayerAverage;
import se.anna.repository.PlayerRepository;

import java.util.List;
import java.util.Scanner;

@Configuration
public class Runner implements CommandLineRunner {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
    playerRepository.menu();
    }
}



