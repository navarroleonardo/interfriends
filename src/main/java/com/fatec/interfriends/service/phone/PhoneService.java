package com.fatec.interfriends.service.phone;

import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
import com.fatec.interfriends.domain.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {

    Phone createPhone(PhoneRequestDto phoneRequestDto);
    Phone getPhone(Long phoneId);
    Page<Phone> getPhoneByUser(Long userId, Pageable pageable);
    Phone updatePhone(Long phoneId, PhoneRequestDto phoneRequestDto);
    Phone deletePhone(Long phoneId);

}
