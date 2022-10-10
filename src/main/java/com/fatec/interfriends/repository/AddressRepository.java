package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.AddressModel;
import com.fatec.interfriends.domain.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    Page<AddressModel> findAllByUser(UserModel userModel, Pageable pageable);

}
