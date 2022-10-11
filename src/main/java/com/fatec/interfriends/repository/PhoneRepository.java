package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Phone;
import com.fatec.interfriends.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Page<Phone> findAllByUser(User user, Pageable pageable);

}
