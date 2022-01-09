package com.pethome.pethome.repository;

import java.util.Optional;

import com.pethome.pethome.models.ERole;
import com.pethome.pethome.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
