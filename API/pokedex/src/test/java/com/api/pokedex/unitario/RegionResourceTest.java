package com.api.pokedex.unitario;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.entities.Region;
import com.api.pokedex.resources.PokemonResource;
import com.api.pokedex.resources.RegionResource;
import com.api.pokedex.services.PokemonService;
import com.api.pokedex.services.RegionService;
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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;


@WebMvcTest(RegionResource.class)
public class RegionResourceTest {

    @Autowired
    RegionResource regionResource;

    @MockBean
    private RegionService regionService;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.regionResource);
    }

    @Test
    @DisplayName("Should find all regions")
    public void findAllTest(){
        List<Region> regionList = new ArrayList<>();
        regionList.add(new Region(null, "test"));
        regionList.add(new Region(null, "test1"));
        regionList.add(new Region(null, "test2"));

        when(regionService.findAll()).thenReturn(regionList);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/region")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Should find a region by id")
    public void findByIdTest(){
        when(this.regionService.findById(1L))
                .thenReturn(new Region(1L, "test"));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/region/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should not find a region by id")
    public void notFindByIdTest(){
        when(this.regionService.findById(6L))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/region/{id}", 6L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Should give error when inserting a region")
    public void notinsertTest(){
        Region r1 = new Region(null, "test");
        when(this.regionService.insert(r1))
                .thenReturn(null);

        given()
                .body(r1)
                .accept(ContentType.JSON)
                .when()
                .post("/region")
                .then()
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

}
