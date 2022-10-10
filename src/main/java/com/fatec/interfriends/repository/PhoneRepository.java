package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.PhoneModel;
import com.fatec.interfriends.domain.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneModel, Long> {

    Page<PhoneModel> findAllByUser(UserModel userModel, Pageable pageable);

}
