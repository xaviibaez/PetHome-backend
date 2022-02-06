package com.pethome.pethome.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pets")
public class Pet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  // @JoinColumn(name = "type_id", referencedColumnName = "id")
  // @JsonManagedReference
  // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  // private TypePet typePet;

  @ManyToOne()
  @JoinColumn(name = "typePet", referencedColumnName = "id")
  private TypePet typePet;

  public Pet() {
  }

  public Pet(String name) {
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

  public TypePet getTypePet(){
    return typePet;
  }

  public void setTypePet(TypePet typePet){
    this.typePet = typePet;
  }
}