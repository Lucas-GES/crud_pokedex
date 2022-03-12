package com.api.pokedex.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Pokemon not found. Id " + id);
    }
}
