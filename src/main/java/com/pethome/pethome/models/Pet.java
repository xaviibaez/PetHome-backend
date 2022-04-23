package com.pethome.pethome.models;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

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

  @NotBlank
  @Size(max = 50)
  private String description;

  @NotBlank
  @Size(max = 20)
  private String behaviour;

  @NotBlank
  private String sex;

  @NotBlank
  private int age;

  @NotBlank
  private boolean sterilized;

  @NotBlank
  private boolean adopted;

  @NotBlank
  private boolean urgentAdoption;


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


  public Pet(String name, String description, String behaviour, int age, boolean sterilized, TypePet typePet) {
    this.name = name;
    this.description = description;
    this.behaviour = behaviour;
    this.age = age;
    this.sterilized = sterilized;
    this.typePet = typePet;
  }
  
  public Pet(Long id, String name, String description, String behaviour, String sex, int age, boolean sterilized, boolean adopted, boolean urgentAdoption, TypePet typePet) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.behaviour = behaviour;
    this.sex = sex;
    this.age = age;
    this.sterilized = sterilized;
    this.adopted = adopted;
    this.urgentAdoption = urgentAdoption;
    this.typePet = typePet;
  }

  public Pet(String name, TypePet typePetDetails) {
    this.name = name;
    this.typePet = typePetDetails;
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


  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBehaviour() {
    return this.behaviour;
  }

  public void setBehaviour(String behaviour) {
    this.behaviour = behaviour;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isSterilized() {
    return this.sterilized;
  }

  public boolean getSterilized() {
    return this.sterilized;
  }

  public void setSterilized(boolean sterilized) {
    this.sterilized = sterilized;
  }

  public boolean isAdopted() {
    return this.adopted;
  }

  public boolean getAdopted() {
    return this.adopted;
  }

  public void setAdopted(boolean adopted) {
    this.adopted = adopted;
  }

  public boolean isUrgentAdoption() {
    return this.urgentAdoption;
  }

  public boolean getUrgentAdoption() {
    return this.urgentAdoption;
  }

  public void setUrgentAdoption(boolean urgentAdoption) {
    this.urgentAdoption = urgentAdoption;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Pet))
      return false;

    Pet pet = (Pet) o;
    return Objects.equals(this.id, pet.id) && Objects.equals(this.name, pet.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return "Pet{" + "id=" + this.id + ", name='" + this.name + '}';
  }
}