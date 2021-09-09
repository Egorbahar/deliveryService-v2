package com.exposit.web.controller;

import com.exposit.core.service.ProductService;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Product;
import com.exposit.persistence.entity.Store;
import com.exposit.web.annotation.LogExecutionTime;
import com.exposit.web.dto.request.StoreRequestDto;
import com.exposit.web.dto.response.StoreResponseDto;
import com.exposit.web.mapper.StoreMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/stores")
@AllArgsConstructor
@Slf4j
public class StoreController {
    private final StoreMapper storeMapper;
    private final StoreService storeService;
    private final ProductService productService;

    @PostMapping
    @LogExecutionTime
    public void save(@Valid @RequestBody StoreRequestDto storeRequestDto) {
        log.info("Executing save method in controller layer...");
        Store store = storeMapper.toStore(storeRequestDto);
        storeService.save(store);
    }

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<StoreResponseDto>> getAll() {
        log.info("Executing getAll method in controller layer...");
        List<StoreResponseDto> storeResponseDtoList = storeMapper.toStoreResponseDtoList(storeService.findAll());
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<StoreResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        log.info("Executing getById method in controller layer...");
        StoreResponseDto storeResponseDto = storeMapper.toStoreResponseDto(storeService.findById(id));
        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public void delete(@PathVariable("id") Long id) {
        log.info("Executing delete method in controller layer...");
        storeService.delete(id);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<StoreResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody StoreRequestDto storeRequestDto) {
        log.info("Executing update method in controller layer...");
        Store store = storeMapper.toStore(storeRequestDto);
        store.setId(id);
        storeService.update(store);
        StoreResponseDto storeResponseDto = storeMapper.toStoreResponseDto(store);
        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);
    }

    @PostMapping("{id}/products")
    @ResponseStatus(value = HttpStatus.OK)
    @LogExecutionTime
    public void addProductToStore(@PathVariable("id") @NotBlank @Positive Long storeId, @Valid @RequestParam Long prodId) {
        log.info("Executing addProductToStore method in controller layer...");
        Product product = productService.findById(prodId);
        Store store = storeService.findById(storeId);
        store.getProducts().add(product);
        storeService.update(store);
    }

    @GetMapping("/priceMin")
    @LogExecutionTime
    public ResponseEntity<List<StoreResponseDto>> findByProductNameWithMinPrice(@Valid @RequestParam String name) {
        log.info("Executing findByProductNameWithMinPrice method in controller layer...");
        List<StoreResponseDto> storeResponseDtoList = storeMapper.toStoreResponseDtoList(storeService.findByProductNameWithMinProductPrice(name));
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/isInStock")
    @LogExecutionTime
    public ResponseEntity<List<StoreResponseDto>> findAllStoresWhereProductIsInStock(@Valid @RequestParam String productName) {
        log.info("Executing findAllStoresWhereProductIsInStock method in controller layer...");
        List<StoreResponseDto> storeResponseDtoList = storeMapper.toStoreResponseDtoList(storeService.findAllStoresWhereProductIsInStock(productName));
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @LogExecutionTime
    public ResponseEntity<List<StoreResponseDto>> getAllByNameOrAddress(@Valid @RequestParam String name, @Valid @RequestParam String address) {
        log.info("Executing getAllByNameOrAddress method in controller layer...");
        List<StoreResponseDto> storeResponseDtoList = storeMapper.toStoreResponseDtoList(storeService.filterByNameOrAddress(name, address));
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }
}
