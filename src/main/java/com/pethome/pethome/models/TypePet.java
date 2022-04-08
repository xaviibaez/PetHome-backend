package com.pethome.pethome.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "type_pets")
public class TypePet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 20)
    private String name;

    // @OneToOne(fetch = FetchType.LAZY, mappedBy = "typePet", cascade = CascadeType.ALL)
    // @JsonBackReference
    // private Pet pet;

    @OneToMany(mappedBy = "typePet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
    
    public TypePet() {
    }

    public TypePet(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TypePet))
            return false;

        TypePet typePet = (TypePet) o;
        return Objects.equals(this.id, typePet.id) && Objects.equals(this.name, typePet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "TypePet{" + "id=" + this.id + ", name='" + this.name + '}';
    }
}
