package com.vicheak.sbwebmvc.controller;

import com.vicheak.sbwebmvc.model.Supplier;
import com.vicheak.sbwebmvc.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public List<Supplier> loadSuppliers() {
        return supplierService.loadSuppliers();
    }

    @PostMapping
    public ResponseEntity<?> addNewSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "You have added supplier successfully");
        data.put("data", supplier);
        data.put("code", 9999);
        data.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateSupplierById(@PathVariable("id") Integer id,
                                   @RequestBody Supplier supplier) {
        supplierService.updateSupplierById(id, supplier);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSupplierById(@PathVariable("id") Integer id) {
        supplierService.deleteSupplierById(id);
    }

    @GetMapping("/{id}")
    public Supplier loadSupplierById(@PathVariable("id") Integer id) {
        return supplierService.loadSupplierById(id);
    }

}
