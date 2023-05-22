package se.anna.repository;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.anna.business.Player;
import se.anna.business.PlayerAverage;

import java.util.List;
import java.util.Scanner;

@Repository
public class PlayerRepository {


    Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.println("Welcome!");
        char option;
        while (true) {
            System.out.println("Please select an option from below");
            System.out.println();
            System.out.println("a) Display a specific player");
            System.out.println("b) Display all players");
            System.out.println("c) Display Top Player");
            System.out.println("d) Create a player");
            System.out.println("e) Update a player");
            System.out.println("f) Delete a player");
            System.out.println("x) Quit");
            
            option = scanner.next().charAt(0);

            switch (option) {
                case 'a' -> {
                    System.out.println("......................");
                    readPlayerToDisplay();
                    System.out.println("......................");
                    System.out.println();
                }
                case 'b' -> {
                    System.out.println("......................");
                    List<Player> players = displayAllPlayers();
                    System.out.println(players);
                    System.out.println("......................");
                    System.out.println();
                }
                case 'c' -> {
                    System.out.println("......................");
                    System.out.println(displayTopPlayer().getPlayer());
                    System.out.println("......................");
                    System.out.println();
                }
                case 'd' -> {
                    System.out.println("......................");
                    createPlayer();
                    System.out.println("......................");
                    System.out.println();
                }
                case 'e' -> {
                    System.out.println("......................");
                    readPlayerToUpdate();
                    System.out.println("......................");
                    System.out.println();
                }
                case 'f' -> {
                    System.out.println("......................");
                    readPlayerToDelete();
                    System.out.println("......................");
                    System.out.println();
                }
                case 'q' -> {
                    System.out.println("Program exiting");
                    System.exit(0);
                }
                default -> System.out.println("Incorrect input. Please select an option from below");
            }
        }
    }

        WebClient client = WebClient.create("http://localhost:8080/rest/");

        private void readPlayerToDisplay() {
            System.out.println("Enter id of player: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            Player player = displayPlayer(id);
            if(player != null){
                System.out.println("Selected player: " );
                System.out.println(player.toString());
            }else {
                System.out.println("There is no such player.");
            }
        }

        private Player displayPlayer(long id){
            try {
                Mono<Player> m = client
                        .get()
                        .uri("getPlayerById/{id}",id)
                        .retrieve()
                        .bodyToMono(Player.class);
                return m.block();
            } catch (Exception e) {
                return null;
            }
        }

    private List<Player> displayAllPlayers() {
        Flux<Player> f = client
                .get()
                .uri("getAllPlayers")
                .retrieve()
                .bodyToFlux(Player.class);
        return f.collectList().block();
    }

    private PlayerAverage displayTopPlayer() {
        Mono<PlayerAverage> m = client
                .get()
                .uri("getTopPlayer")
                .retrieve()
                .bodyToMono(PlayerAverage.class);
        return m.block();
    }


    private boolean createPlayer() {
        System.out.println("Enter player name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        try {
            Mono<Player> m = client
                    .post()
                    .uri("createPlayer")
                    .bodyValue(player)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Player.class);
            m.block();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private void readPlayerToUpdate() {
        System.out.println("Select what player id you would like to update");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter the new payer name");
        String name = scanner.nextLine();
        if (updatePlayer(id, name)){
            System.out.println("Player has been successfully updated");
        }else {
            System.out.println("There is no such player");
        }
    }
    private boolean updatePlayer(long id, String name) {
        try {
            Mono<String> m = client
                    .put()
                    .uri("updatePlayer/{id}/{name}", id, name)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);
            m.block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void readPlayerToDelete() {
        System.out.println("Enter the id of the player you would like to delete: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        if(deletePlayer(id)){
            System.out.println("The selected player has been deleted: ");
        }else {
            System.out.println("There is no such player.");
        }
    }

    private boolean deletePlayer(long id){
        try {
            Mono<String> m = client
                    .delete()
                    .uri("deletePlayer/{id}",id)
                    .retrieve()
                    .bodyToMono(String.class);
            m.block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}





