package com.api.pokedex.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import repositories.PokemonRepository;
import repositories.RegionRepository;

@Configuration
@Profile("dev")
public class Program implements CommandLineRunner {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Here");
//        Pokemon pokemon1 = new Pokemon(null, "Heracross", "Inseto", 90.1);
//        System.out.println(pokemon1.getName());
//        pokemon1.setRegion(regionRepository.getById(2));
//        pokemonRepository.save(pokemon1);
//        System.out.println("Saved");
        System.out.println(pokemonRepository.findById(1L).map(x -> x.getName()));

    }

}
