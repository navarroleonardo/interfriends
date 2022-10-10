package com.fatec.interfriends.service.phone;

import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
import com.fatec.interfriends.domain.model.PhoneModel;
import com.fatec.interfriends.domain.model.UserModel;
import com.fatec.interfriends.repository.PhoneRepository;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final UserService userService;

    public PhoneServiceImpl(PhoneRepository phoneRepository, UserService userService) {
        this.phoneRepository = phoneRepository;
        this.userService = userService;
    }

    @Override
    public PhoneModel createPhone(PhoneRequestDto phoneRequestDto) {
        PhoneModel phoneModel = new PhoneModel(phoneRequestDto);

        UserModel userModel = this.userService.getUser(phoneRequestDto.getUserId());
        phoneModel.setUser(userModel);

        return this.phoneRepository.save(phoneModel);
    }

    @Override
    public PhoneModel getPhone(Long phoneId) {
        Optional<PhoneModel> optionalPhoneModel = this.phoneRepository.findById(phoneId);

        if (!optionalPhoneModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        return optionalPhoneModel.get();
    }

    @Override
    public Page<PhoneModel> getPhoneByUser(Long userId, Pageable pageable) {
        UserModel userModel = this.userService.getUser(userId);

        return this.phoneRepository.findAllByUser(userModel, pageable);
    }

    @Override
    public PhoneModel updatePhone(Long phoneId, PhoneRequestDto phoneRequestDto) {
        Optional<PhoneModel> optionalPhoneModel = this.phoneRepository.findById(phoneId);

        if (!optionalPhoneModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        PhoneModel phoneModel = new PhoneModel(phoneRequestDto);

        UserModel userModel = this.userService.getUser(phoneRequestDto.getUserId());
        phoneModel.setUser(userModel);
        phoneModel.setPhoneId(optionalPhoneModel.get().getPhoneId());

        return this.phoneRepository.save(phoneModel);
    }

    @Override
    public PhoneModel deletePhone(Long phoneId) {
        Optional<PhoneModel> optionalPhoneModel = this.phoneRepository.findById(phoneId);

        if (!optionalPhoneModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        this.phoneRepository.delete(optionalPhoneModel.get());

        return optionalPhoneModel.get();
    }

}
