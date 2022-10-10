package com.fatec.interfriends.service.address;

import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import com.fatec.interfriends.domain.model.AddressModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    AddressModel createAddress(AddressRequestDto addressRequestDto);
    AddressModel getAddress(Long addressId);
    Page<AddressModel> getAddressByUser(Long userId, Pageable pageable);
    AddressModel updateAddress(Long addressId, AddressRequestDto addressRequestDto);
    AddressModel deleteAddress(Long addressId);

}
