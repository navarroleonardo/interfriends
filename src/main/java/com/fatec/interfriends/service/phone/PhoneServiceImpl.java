package com.fatec.interfriends.service.phone;

import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
import com.fatec.interfriends.domain.model.Phone;
import com.fatec.interfriends.domain.model.User;
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
    public Phone createPhone(PhoneRequestDto phoneRequestDto) {
        Phone phone = new Phone(phoneRequestDto);

        User user = this.userService.getUser(phoneRequestDto.getUserId());
        phone.setUser(user);

        return this.phoneRepository.save(phone);
    }

    @Override
    public Phone getPhone(Long phoneId) {
        Optional<Phone> optionalPhone = this.phoneRepository.findById(phoneId);

        if (!optionalPhone.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        return optionalPhone.get();
    }

    @Override
    public Page<Phone> getPhoneByUser(Long userId, Pageable pageable) {
        User user = this.userService.getUser(userId);

        return this.phoneRepository.findAllByUser(user, pageable);
    }

    @Override
    public Phone updatePhone(Long phoneId, PhoneRequestDto phoneRequestDto) {
        Optional<Phone> optionalPhone = this.phoneRepository.findById(phoneId);

        if (!optionalPhone.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        Phone phone = new Phone(phoneRequestDto);

        User user = this.userService.getUser(phoneRequestDto.getUserId());
        phone.setUser(user);
        phone.setPhoneId(optionalPhone.get().getPhoneId());

        return this.phoneRepository.save(phone);
    }

    @Override
    public Phone deletePhone(Long phoneId) {
        Optional<Phone> optionalPhone = this.phoneRepository.findById(phoneId);

        if (!optionalPhone.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado.");
        }

        this.phoneRepository.delete(optionalPhone.get());

        return optionalPhone.get();
    }

}
