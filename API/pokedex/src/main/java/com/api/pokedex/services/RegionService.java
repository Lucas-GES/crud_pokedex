package com.api.pokedex.services;

import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.RegionRepository;
import com.api.pokedex.services.exceptions.DatabaseException;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {


    private RegionRepository repository;

    @Autowired
    public RegionService(RegionRepository regionRepository){
        this.repository = regionRepository;
    }

    public List<Region> findAll(){
        return repository.findAll();
    }

    public Region findById(Long id){
        Optional<Region> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Region insert(Region obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Region update(Long id, Region obj){
        try{
            Region entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Region entity, Region obj) {
        entity.setName(obj.getName());
    }

}
