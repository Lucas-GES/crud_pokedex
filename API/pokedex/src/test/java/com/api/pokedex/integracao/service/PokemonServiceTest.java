package com.api.pokedex.integracao.service;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.PokemonRepository;
import com.api.pokedex.services.PokemonService;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void testInsertPokemon(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        Pokemon pokemon = new Pokemon(null, "img", "test", "test", 0.0, new Region());
        pokemonRepository.save(pokemon);
        int countService = pokemonService.findAll().size();
        int count = pokemonRepository.findAll().size();
        assertEquals(countService, count);
    }

    @Test
    public void testNotFindId(){
        Pokemon pokemon = new Pokemon(null, "img", "test", "test", 0.0, new Region());
        pokemonRepository.save(pokemon);
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        assertThrows(ResourceNotFoundException.class, () -> pokemonService.findById(99L));
    }

    @Test
    public void testFindId(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        Pokemon p1 = pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0, new Region()));
        Optional<Pokemon> id = pokemonRepository.findById(p1.getId());
        Optional<Pokemon> resultId = Optional.ofNullable(pokemonService.findById(p1.getId()));

        assertEquals(id, resultId);
    }

    @Test
    public void testFindAll(){
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon p1 = new Pokemon(null, "img", "test1", "tipo", 0.0, new Region());
        Pokemon p2 = new Pokemon(null, "img", "test2", "tipo", 0.0, new Region());
        Pokemon p3 = new Pokemon(null, "img", "test3", "tipo", 0.0, new Region());
        pokemonList.addAll(Arrays.asList(p1, p2, p3));
        pokemonRepository.saveAll(Arrays.asList(p1, p2, p3));

        assertEquals(pokemonList, pokemonRepository.findAll());
    }

    @Test
    public void testDeletePokemon(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0, new Region()));
        int fullSizeDB = pokemonService.findAll().size();
        pokemonService.delete(1L);
        int sizeAfterDeletion = pokemonService.findAll().size();
        assertNotEquals(sizeAfterDeletion, fullSizeDB);
    }

    @Test
    public void testNotDeletePokemon(){
        PokemonService pokemonService = new PokemonService(pokemonRepository);
        pokemonRepository.save(new Pokemon(null, "img", "test", "tipo", 0.0, new Region()));
        assertThrows(ResourceNotFoundException.class, () -> pokemonService.delete(5L));
    }

}
