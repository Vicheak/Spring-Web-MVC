package com.vicheak.sbwebmvc.controller;

import com.vicheak.sbwebmvc.model.Supplier;
import com.vicheak.sbwebmvc.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public List<Supplier> loadSuppliers() {
        return supplierService.loadSuppliers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addNewSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateSupplierById(@PathVariable("id") Integer id,
                                   @RequestBody Supplier supplier) {
        supplierService.updateSupplierById(id, supplier);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSupplierById(@PathVariable("id") Integer id){
        supplierService.deleteSupplierById(id);
    }

    @GetMapping("/{id}")
    public Supplier loadSupplierById(@PathVariable("id") Integer id){
        return supplierService.loadSupplierById(id);
    }

}
