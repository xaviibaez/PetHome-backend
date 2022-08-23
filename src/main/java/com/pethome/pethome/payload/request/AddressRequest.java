package com.pethome.pethome.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressRequest {
    @NotBlank
    @Size(max = 20)
  	private String name;

	@NotBlank
    @Size(max = 50)
	private String description;

    @NotBlank
	private Long locationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    
}
