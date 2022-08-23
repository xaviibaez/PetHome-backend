package com.pethome.pethome.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PetRequest {
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
	private String typePet;

    private int age;

    private boolean adopted;

    private boolean urgentAdoption;

    private boolean sterilized;

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
}
