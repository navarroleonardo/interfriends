package com.fatec.interfriends.service.size;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.SizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public SizeResponseDto createSize(SizeRequestDto sizeRequestDto) {
        SizeModel sizeModel = new SizeModel(sizeRequestDto);

        return new SizeResponseDto(this.sizeRepository.save(sizeModel));
    }

    @Override
    public SizeResponseDto getSize(Long id) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (!sizeModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        return new SizeResponseDto(sizeModelOptional.get());
    }

    @Override
    public SizeResponseDto updateSize(Long id, SizeRequestDto sizeRequestDto) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (!sizeModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        SizeModel sizeModel = new SizeModel(sizeRequestDto);
        sizeModel.setSizeId(sizeModelOptional.get().getSizeId());

        return new SizeResponseDto(this.sizeRepository.save(sizeModel));
    }

    @Override
    public SizeResponseDto deleteSize(Long id) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (!sizeModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        this.sizeRepository.delete(sizeModelOptional.get());

        return new SizeResponseDto(sizeModelOptional.get());
    }
}
