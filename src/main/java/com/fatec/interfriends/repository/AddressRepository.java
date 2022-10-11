package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Address;
import com.fatec.interfriends.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findAllByUser(User user, Pageable pageable);

}
