package com.pethome.pethome.models;

import javax.persistence.*;

@Entity
@Table(name = "type_pets")
public class TypePet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 20)
    private String name;

    @OneToOne(mappedBy = "typePet")
    private Pet pet;

    public TypePet() {

    }

    public TypePet(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
