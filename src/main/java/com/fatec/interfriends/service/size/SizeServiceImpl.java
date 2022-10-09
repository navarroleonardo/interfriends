package com.fatec.interfriends.service.size;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;
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
    public SizeModel createSize(SizeRequestDto sizeRequestDto) {
        SizeModel sizeModel = new SizeModel(sizeRequestDto);

        return this.sizeRepository.save(sizeModel);
    }

    @Override
    public SizeModel getSize(Long id) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (sizeModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        return sizeModelOptional.get();
    }

    @Override
    public List<SizeModel> getSizesById(List<Long> sizeIds) {
        return sizeIds.stream()
                .map(this.sizeRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public List<SizeModel> getSizesByProductSizes(List<ProductSizeModel> productSizeModels) {
        return productSizeModels.stream()
                .map(ProductSizeModel::getSize)
                .toList();
    }

    @Override
    public SizeModel updateSize(Long id, SizeRequestDto sizeRequestDto) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (sizeModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        SizeModel sizeModel = new SizeModel(sizeRequestDto);
        sizeModel.setSizeId(sizeModelOptional.get().getSizeId());

        return this.sizeRepository.save(sizeModel);
    }

    @Override
    public SizeModel deleteSize(Long id) {
        Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(id);

        if (sizeModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho não encontrado.");
        }

        this.sizeRepository.delete(sizeModelOptional.get());

        return sizeModelOptional.get();
    }
}
