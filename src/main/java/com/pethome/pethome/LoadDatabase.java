package com.pethome.pethome;

import com.pethome.pethome.models.Role;
import com.pethome.pethome.models.TypePet;
import com.pethome.pethome.models.ERole;
import com.pethome.pethome.repository.IRoleRepository;
import com.pethome.pethome.repository.ITypePetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Bean
  CommandLineRunner initDatabaseRole(IRoleRepository roleRepository) {
    if(!roleRepository.findAll().isEmpty()){
      return args -> {
        log.info("No roles to insert");
      };    
    }

    return args -> {
      log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_USER)));
      log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_MODERATOR)));
      log.info("Preloading " + roleRepository.save(new Role(ERole.ROLE_ADMIN)));
    };
  }

  @Bean
  CommandLineRunner initDatabaseTypePet(ITypePetRepository typePetRepository) {
    if(!typePetRepository.findAll().isEmpty()){
      return args -> {
        log.info("No type pets to insert");
      };    
    }

    return args -> {
      log.info("Preloading " + typePetRepository.save(new TypePet("Dog")));
      log.info("Preloading " + typePetRepository.save(new TypePet("Cat")));
      log.info("Preloading " + typePetRepository.save(new TypePet("Fish")));
    };
  }
}
