package com.api.pokedex.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Pokemon not found. Id " + id);
    }
}
