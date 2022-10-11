package com.fatec.interfriends.service.address;

import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import com.fatec.interfriends.domain.model.Address;
import com.fatec.interfriends.domain.model.User;
import com.fatec.interfriends.repository.AddressRepository;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public Address createAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address(addressRequestDto);

        User user = this.userService.getUser(addressRequestDto.getUserId());
        address.setUser(user);

        return this.addressRepository.save(address);
    }

    @Override
    public Address getAddress(Long addressId) {
        Optional<Address> optionalAddress = this.addressRepository.findById(addressId);

        if (!optionalAddress.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        return optionalAddress.get();
    }

    @Override
    public Page<Address> getAddressByUser(Long userId, Pageable pageable) {
        User user = this.userService.getUser(userId);

        return this.addressRepository.findAllByUser(user, pageable);
    }

    @Override
    public Address updateAddress(Long addressId, AddressRequestDto addressRequestDto) {
        Optional<Address> optionalAddress = this.addressRepository.findById(addressId);

        if (!optionalAddress.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        Address address = new Address(addressRequestDto);

        User user = this.userService.getUser(addressRequestDto.getUserId());
        address.setUser(user);
        address.setAddressId(optionalAddress.get().getAddressId());

        return this.addressRepository.save(address);
    }

    @Override
    public Address deleteAddress(Long addressId) {
        Optional<Address> optionalAddress = this.addressRepository.findById(addressId);

        if (!optionalAddress.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        this.addressRepository.delete(optionalAddress.get());

        return optionalAddress.get();
    }

}
