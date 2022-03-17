package com.api.pokedex.unitario;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.PokemonRepository;
import com.api.pokedex.repositories.RegionRepository;
import com.api.pokedex.services.PokemonService;
import com.api.pokedex.services.RegionService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Region Service Class Test")
public class RegionServiceTest {

    @InjectMocks
    RegionService regionService;

    @Mock
    RegionRepository regionRepository;

    @Test
    @DisplayName("Should find all regions")
    public void findAllTest(){
        List<Region> regionList = new ArrayList<>();
        Region r1 = Mockito.mock(Region.class);
        Region r2 = Mockito.mock(Region.class);
        Region r3 = Mockito.mock(Region.class);
        regionList.add(r1);
        regionList.add(r2);
        regionList.add(r3);
        regionRepository.saveAll(Arrays.asList(r1, r2, r3));
        when(regionRepository.findAll()).thenReturn(regionList);
        List<Region> result = regionService.findAll();

        assertEquals(result, regionList);
        verify(regionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find a region by id")
    public void findByIdTest(){
        Optional<Region> region = Optional.of(new Region(1L,"test"));
        when(regionRepository.findById(1L)).thenReturn(region);
        Optional<Region> result = Optional.ofNullable(regionService.findById(1L));

        assertEquals(result, region);
    }

    @Test
    @DisplayName("Should not find a region by id")
    public void notFindByIdTest(){
        Region region = Mockito.mock(Region.class);
        regionService.insert(region);
        assertThrows(ResourceNotFoundException.class, ()-> regionService.findById(2L));
    }

    @Test
    @DisplayName("Should insert a region")
    public void insertTest(){
        Region r1 = Mockito.mock(Region.class);
        regionService.insert(r1);
        verify(regionRepository, times(1)).save(r1);
    }

    @Test
    @DisplayName("Should delete a region by id")
    public void deletePokemonTest(){
        Region r1 = Mockito.mock(Region.class);
        regionService.insert(r1);
        regionService.delete(r1.getId());
        verify(regionRepository, times(1)).deleteById(r1.getId());
    }

}
