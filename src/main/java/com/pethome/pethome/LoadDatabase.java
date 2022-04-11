/*package com.pethome.pethome;

import com.pethome.pethome.models.Role;
import com.pethome.pethome.models.TypePet;
import com.pethome.pethome.models.ERole;
import com.pethome.pethome.models.Pet;
import com.pethome.pethome.repository.PetRepository;
import com.pethome.pethome.repository.RoleRepository;
import com.pethome.pethome.repository.TypePetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Autowired
	private TypePetRepository typePetRepository;

  @Bean
  CommandLineRunner initDatabaseRole(RoleRepository roleRepository) {
    return args -> {
      if(!roleRepository.existsByName((long) 1)){
        log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_USER)));
        log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_MODERATOR)));
        log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_ADMIN)));
      }
    };
  }

  @Bean
  CommandLineRunner initDatabaseTypePet(TypePetRepository typePetRepository) {
    return args -> {
      log.info("Preloading " + typePetRepository.save(new TypePet("Dog")));
      log.info("Preloading " + typePetRepository.save(new TypePet("Cat")));
    };
  }

  @Bean
  CommandLineRunner initDatabasePet(PetRepository petRepository) {
    return args -> {
      log.info("Preloading " + petRepository.save(new Pet("Perrito", "Descripcion Perro", "Comportamiento Perro", 1, true, typePetRepository.findByName("Dog").get())));
      log.info("Preloading " + petRepository.save(new Pet("Gatito", "Descripcion Gato", "Comportamiento Gato", 2, false, typePetRepository.findByName("Cat").get())));
    };
  }
}
*/