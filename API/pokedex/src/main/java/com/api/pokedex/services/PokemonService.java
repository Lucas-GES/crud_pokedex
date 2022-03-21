package com.api.pokedex.services;

import com.api.pokedex.entities.Pokemon;
import com.api.pokedex.entities.Region;
import com.api.pokedex.repositories.PokemonRepository;
import com.api.pokedex.services.exceptions.DatabaseException;
import com.api.pokedex.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private PokemonRepository repository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository){
        this.repository = pokemonRepository;
    }

    public List<Pokemon> findAll(){
        return repository.findAll();
    }

    public Pokemon findById(Long id){
        Optional<Pokemon> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Pokemon> findByRegionId(Region id){
        return repository.findAll()
                .stream()
                .filter(pokemon -> pokemon.getRegion().getId() == id.getId())
                .collect(Collectors.toList());
    }

    public Pokemon insert(Pokemon obj){
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

    public Pokemon update(Long id, Pokemon obj){
        try{
            Pokemon entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Pokemon entity, Pokemon obj) {
        entity.setImg(obj.getImg());
        entity.setName(obj.getName());
        entity.setTipo(obj.getTipo());
        entity.setIv(obj.getIv());
        entity.setRegion(obj.getRegion());
    }

}
