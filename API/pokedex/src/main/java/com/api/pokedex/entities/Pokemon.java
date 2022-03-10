package com.api.pokedex.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pokemon")
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nome;
    private String tipo;
    private Double iv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, String tipo, Double iv) {
        this.id = id;
        this.Nome = name;
        this.tipo = tipo;
        this.iv = iv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Nome;
    }

    public void setName(String name) {
        this.Nome = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getIv() {
        return iv;
    }

    public void setIv(Double iv) {
        this.iv = iv;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return id.equals(pokemon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}