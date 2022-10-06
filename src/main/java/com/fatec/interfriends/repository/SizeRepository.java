package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeModel, Long> {
}
