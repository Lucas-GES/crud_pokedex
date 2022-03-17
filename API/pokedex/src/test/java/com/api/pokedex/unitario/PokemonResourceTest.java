package com.api.pokedex.unitario;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.resources.PokemonResource;
import com.api.pokedex.services.PokemonService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Mockito.when;


@WebMvcTest(PokemonResource.class)
public class PokemonResourceTest {

    @Autowired
    PokemonResource pokemonResource;

    @MockBean
    private PokemonService service;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.pokemonResource);
    }

    @Test
    @DisplayName("Should find all pokemon")
    public void findAllTest(){
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon(null, "img", "test", "tipo", 0.0));
        pokemonList.add(new Pokemon(null, "img", "test1", "tipo", 0.0));
        pokemonList.add(new Pokemon(null, "img", "test2", "tipo", 0.0));

        when(service.findAll()).thenReturn(pokemonList);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/pokemon")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Should find a pokemon by id")
    public void findByIdTest(){
        when(this.service.findById(1L))
                .thenReturn(new Pokemon(1L, "img", "test", "tipo", 0.0));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/pokemon/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should not find a pokemon by id")
    public void notFindByIdTest(){
        when(this.service.findById(6L))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/pokemon/{id}", 6L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Should give error when inserting a pokemon")
    public void notinsertTest(){
        Pokemon p1 = new Pokemon(null, "img", "test", "tipo", 0.0);
        when(this.service.insert(p1))
                .thenReturn(null);

        given()
                .body(p1)
                .accept(ContentType.JSON)
                .when()
                .post("/pokemon")
                .then()
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

}
