package com.fatec.interfriends.service.address;

import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import com.fatec.interfriends.domain.model.AddressModel;
import com.fatec.interfriends.domain.model.UserModel;
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
    public AddressModel createAddress(AddressRequestDto addressRequestDto) {
        AddressModel addressModel = new AddressModel(addressRequestDto);

        UserModel userModel = this.userService.getUser(addressRequestDto.getUserId());
        addressModel.setUser(userModel);

        return this.addressRepository.save(addressModel);
    }

    @Override
    public AddressModel getAddress(Long addressId) {
        Optional<AddressModel> optionalAddressModel = this.addressRepository.findById(addressId);

        if (!optionalAddressModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        return optionalAddressModel.get();
    }

    @Override
    public Page<AddressModel> getAddressByUser(Long userId, Pageable pageable) {
        UserModel userModel = this.userService.getUser(userId);

        return this.addressRepository.findAllByUser(userModel, pageable);
    }

    @Override
    public AddressModel updateAddress(Long addressId, AddressRequestDto addressRequestDto) {
        Optional<AddressModel> optionalAddressModel = this.addressRepository.findById(addressId);

        if (!optionalAddressModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        AddressModel addressModel = new AddressModel(addressRequestDto);

        UserModel userModel = this.userService.getUser(addressRequestDto.getUserId());
        addressModel.setUser(userModel);
        addressModel.setAddressId(optionalAddressModel.get().getAddressId());

        return this.addressRepository.save(addressModel);
    }

    @Override
    public AddressModel deleteAddress(Long addressId) {
        Optional<AddressModel> optionalAddressModel = this.addressRepository.findById(addressId);

        if (!optionalAddressModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        this.addressRepository.delete(optionalAddressModel.get());

        return optionalAddressModel.get();
    }

}
