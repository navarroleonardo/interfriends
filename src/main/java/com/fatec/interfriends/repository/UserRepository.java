package com.fatec.interfriends.repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.fatec.interfriends.domain.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	Optional<UserModel> findByEmail(String email);

}