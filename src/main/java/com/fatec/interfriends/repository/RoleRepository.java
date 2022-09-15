package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findRoleByRoleName(String name);

}
