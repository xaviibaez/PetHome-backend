package com.pethome.pethome.payload.Pet;

import javax.validation.constraints.NotBlank;

public class PetRequest {
    @NotBlank
  	private String name;

	@NotBlank
	private String description;

    @NotBlank
	private String behaviour;

    @NotBlank
	private int age;

    @NotBlank
	private boolean sterilized;

    @NotBlank
	private String typePet;


    public String getName() {
        return this.name;
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

    public String getTypePet() {
        return this.typePet;
    }

    public void setTypePet(String typePet) {
        this.typePet = typePet;
    }

}
