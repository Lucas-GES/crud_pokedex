package com.api.pokedex.unitario;
import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.PokemonRepository;
import com.api.pokedex.services.PokemonService;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pokemon Service Class Test")
public class PokemonServiceTest{

    @InjectMocks
    PokemonService pokemonService;

    @Mock
    PokemonRepository pokemonRepository;

    @Test
    @DisplayName("Should find all pokemons")
    public void findAllTest(){
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon p1 = Mockito.mock(Pokemon.class);
        Pokemon p2 = Mockito.mock(Pokemon.class);
        Pokemon p3 = Mockito.mock(Pokemon.class);
        pokemonList.add(p1);
        pokemonList.add(p2);
        pokemonList.add(p3);
        pokemonRepository.saveAll(Arrays.asList(p1, p2, p3));
        when(pokemonRepository.findAll()).thenReturn(pokemonList);
        List<Pokemon> result = pokemonService.findAll();

        assertEquals(result, pokemonList);
        verify(pokemonRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find pokemon by id")
    public void findByIdTest(){
        Optional<Pokemon> pokemon = Optional.of(new Pokemon(1L, "img", "test", "tipo", 0.0, new Region()));
        when(pokemonRepository.findById(1L)).thenReturn(pokemon);
        Optional<Pokemon> result = Optional.ofNullable(pokemonService.findById(1L));

        assertEquals(result, pokemon);
    }

    @Test
    @DisplayName("Should not find pokemon by id")
    public void notFindByIdTest(){
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        pokemonService.insert(pokemon);
        assertThrows(ResourceNotFoundException.class, ()-> pokemonService.findById(2L));
    }

    @Test
    @DisplayName("Should insert a pokemon")
    public void insertTest(){
        Pokemon p1 = Mockito.mock(Pokemon.class);
        pokemonService.insert(p1);
        verify(pokemonRepository, times(1)).save(p1);
    }

    @Test
    @DisplayName("Should delete a pokemon by id")
    public void deletePokemonTest(){
        Pokemon p1 = Mockito.mock(Pokemon.class);
        pokemonService.insert(p1);
        pokemonService.delete(p1.getId());
        verify(pokemonRepository, times(1)).deleteById(p1.getId());
    }

}
