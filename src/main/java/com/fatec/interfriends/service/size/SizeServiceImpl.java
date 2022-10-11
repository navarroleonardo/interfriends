package com.fatec.interfriends.service.size;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;
import com.fatec.interfriends.repository.SizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Size createSize(SizeRequestDto sizeRequestDto) {
        Size size = new Size(sizeRequestDto);

        return this.sizeRepository.save(size);
    }

    @Override
    public Size getSize(Long id) {
        Optional<Size> optionalSize = this.sizeRepository.findById(id);

        if (optionalSize.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        return optionalSize.get();
    }

    @Override
    public List<Size> getSizesById(List<Long> sizeIds) {
        return sizeIds.stream()
                .map(this.sizeRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public List<Size> getSizesByProductSizes(List<ProductSize> productSizes) {
        return productSizes.stream()
                .map(ProductSize::getSize)
                .toList();
    }

    @Override
    public Size updateSize(Long id, SizeRequestDto sizeRequestDto) {
        Optional<Size> optionalSize = this.sizeRepository.findById(id);

        if (optionalSize.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        Size size = new Size(sizeRequestDto);
        size.setSizeId(optionalSize.get().getSizeId());

        return this.sizeRepository.save(size);
    }

    @Override
    public Size deleteSize(Long id) {
        Optional<Size> optionalSize = this.sizeRepository.findById(id);

        if (optionalSize.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        this.sizeRepository.delete(optionalSize.get());

        return optionalSize.get();
    }
}
