package com.fatec.interfriends.service.phone;

import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
import com.fatec.interfriends.domain.model.PhoneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {

    PhoneModel createPhone(PhoneRequestDto phoneRequestDto);
    PhoneModel getPhone(Long phoneId);
    Page<PhoneModel> getPhoneByUser(Long userId, Pageable pageable);
    PhoneModel updatePhone(Long phoneId, PhoneRequestDto phoneRequestDto);
    PhoneModel deletePhone(Long phoneId);

}
