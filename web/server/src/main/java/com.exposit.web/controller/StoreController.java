package com.exposit.web.controller;

import com.exposit.core.service.ProductService;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Product;
import com.exposit.persistence.entity.Store;
import com.exposit.web.dto.request.StoreRequestDto;
import com.exposit.web.dto.response.StoreResponseDto;
import com.exposit.web.mapper.StoreMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stores")
@AllArgsConstructor
public class StoreController {
    private final StoreMapper storeMapper;
    private final StoreService storeService;
    private final ProductService productService;

    @PostMapping
    public void save(@Valid @RequestBody StoreRequestDto storeRequestDto) {
        Store store = storeMapper.toStore(storeRequestDto);
        storeService.add(store);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getAll() {
        List<StoreResponseDto> storeResponseDtoList = storeMapper.toStoreResponseDtoList(storeService.getAll());
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDto> getById(@PathVariable("id") Long id) {
        StoreResponseDto storeResponseDto = storeMapper.toStoreResponseDto(storeService.findById(id));
        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        storeService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDto> update(@PathVariable("id") Long id, @Valid @RequestBody StoreRequestDto storeRequestDto) {
        Store store = storeMapper.toStore(storeRequestDto);
        store.setId(id);
        storeService.update(store);
        StoreResponseDto storeResponseDto = storeMapper.toStoreResponseDto(store);
        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);
    }

    @PostMapping("{id}/products")
    @ResponseStatus(value = HttpStatus.OK)
    public void addProductToStore(@PathVariable("id") Long storeId, @Valid @RequestParam Long prodId) {
        Product product = productService.findById(prodId);
        Store store = storeService.findById(storeId);
        store.getProducts().add(product);
        storeService.update(store);
    }

}
