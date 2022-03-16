package com.api.pokedex.service;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.repositories.PokemonRepository;
import com.api.pokedex.services.PokemonService;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void testInsertPokemon(){
        Pokemon pokemon = new Pokemon(null, "test1", "test", "test", 0.0);
        pokemonRepository.save(pokemon);
        int count = pokemonRepository.findAll().size();
        assertEquals(1, count);
    }

    @Test
    public void testNotFindId(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        assertThrows(ResourceNotFoundException.class, () -> pokemonService.findById(99L));
    }

    @Test
    public void testFindId(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0));
        Optional<Pokemon> id = pokemonRepository.findById(1L);
        Optional<Pokemon> resultId = Optional.ofNullable(pokemonService.findById(1L));

        assertEquals(id, resultId);
    }

    @Test
    public void testFindAll(){
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon p1 = new Pokemon(null, "img", "test1", "tipo", 0.0);
        Pokemon p2 = new Pokemon(null, "img", "test2", "tipo", 0.0);
        Pokemon p3 = new Pokemon(null, "img", "test3", "tipo", 0.0);
        pokemonList.addAll(Arrays.asList(p1, p2, p3));
        pokemonRepository.saveAll(Arrays.asList(p1, p2, p3));

        assertEquals(pokemonList, pokemonRepository.findAll());
    }

    @Test
    public void testDeletePokemon(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0));
        pokemonService.delete(1L);
        assertEquals(0, pokemonService.findAll().size());
    }

    @Test
    public void testNotDeletePokemon(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0));
        assertThrows(ResourceNotFoundException.class, () -> pokemonService.delete(5L));
    }

}
