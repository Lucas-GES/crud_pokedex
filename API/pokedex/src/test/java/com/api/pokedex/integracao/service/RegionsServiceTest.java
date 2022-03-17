package com.api.pokedex.integracao.service;

import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.RegionRepository;
import com.api.pokedex.services.RegionService;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class RegionsServiceTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testFindAllRegion(){
        RegionService regionService = new RegionService(regionRepository);
        List<Region> regionList = new ArrayList<>();
        Region r1 = new Region(null,"test1");
        Region r2 = new Region(null,"test2");
        Region r3 = new Region(null,"test3");

        regionRepository.saveAll(Arrays.asList(r1, r2, r3));
        regionList.addAll(regionService.findAll());

        assertEquals(regionList, regionRepository.findAll());
    }

    @Test
    public void testInsertRegion(){
        int sizeBeforeInsert = regionRepository.findAll().size();
        Region region = new Region(null, "test1");
        regionRepository.save(region);
        int sizeAfterInsert = regionRepository.findAll().size();
        assertNotEquals(sizeBeforeInsert, sizeAfterInsert);
    }

    @Test
    public void testNotFindRegionId(){
        RegionService regionService = new RegionService(regionRepository);
        Region region = new Region(null, "test1");
        regionRepository.save(region);
        assertThrows(ResourceNotFoundException.class, () -> regionService.findById(99L));
    }

    @Test
    public void testFindRegionId(){
        RegionService regionService = new RegionService(regionRepository);
        Region r1 = regionRepository.save(new Region(null, "test"));
        Optional<Region> id = regionRepository.findById(r1.getId());
        Optional<Region> resultId = Optional.ofNullable(regionService.findById(r1.getId()));

        assertEquals(id, resultId);
    }

    @Test
    public void testDeleteRegion(){
        RegionService regionService = new RegionService(regionRepository);
        regionRepository.save(new Region(null,"test"));
        regionService.delete(1L);
        assertEquals(0, regionService.findAll().size());
    }

    @Test
    public void testNotDeleteRegion(){
        RegionService regionService = new RegionService(regionRepository);
        regionRepository.save(new Region(null,"test"));
        assertThrows(ResourceNotFoundException.class, () -> regionService.delete(0L));
    }

}
