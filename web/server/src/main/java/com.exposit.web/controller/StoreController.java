package com.exposit.web.controller;

import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;
import com.exposit.web.dto.StoreDto;
import com.exposit.web.mapper.StoreMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
@AllArgsConstructor
public class StoreController {
    private final StoreMapper storeMapper;
    private final StoreService storeService;
    @PostMapping
    public void save(@Valid @RequestBody StoreDto storeDto) {
        Store store = storeMapper.toStore(storeDto);
        storeService.add(store);
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll() {
        List<StoreDto> productDtoList = storeMapper.toStoreDtoList(storeService.getAll());
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getById(@PathVariable("id") Long id) {
        StoreDto storeDto = storeMapper.toStoreDto(storeService.findById(id));
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) {
        storeService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody StoreDto storeDto) {
        Store store = storeMapper.toStore(storeDto);
        storeService.update(store);
        return ResponseEntity.ok(storeDto);
    }
}
