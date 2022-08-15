package com.pethome.pethome.models;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Locations")
public class Location {
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

  @OneToOne
  @JoinColumn(name = "address_id")
  //@RestResource(path = "locationAddress", rel="address")
  private Address address;

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

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
  
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Location))
      return false;

    Location pet = (Location) o;
    return Objects.equals(this.id, pet.id) && Objects.equals(this.name, pet.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return "locations {" + "id=" + this.id + ", name='" + this.name + '}';
  }

  
}