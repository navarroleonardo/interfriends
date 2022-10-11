package com.fatec.interfriends.service.address;

import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import com.fatec.interfriends.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    Address createAddress(AddressRequestDto addressRequestDto);
    Address getAddress(Long addressId);
    Page<Address> getAddressByUser(Long userId, Pageable pageable);
    Address updateAddress(Long addressId, AddressRequestDto addressRequestDto);
    Address deleteAddress(Long addressId);

}
