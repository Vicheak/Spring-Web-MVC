package com.vicheak.sbwebmvc.service.impl;

import com.vicheak.sbwebmvc.model.Supplier;
import com.vicheak.sbwebmvc.repository.SupplierRepository;
import com.vicheak.sbwebmvc.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> loadSuppliers() {
        return supplierRepository.select();
    }

    @Override
    public void addNewSupplier(Supplier supplier) {
        supplier.setSince(LocalDate.now());
        supplier.setStatus(true);
        supplierRepository.insert(supplier);
    }

    @Override
    public void updateSupplierById(Integer id, Supplier supplier) {
        supplier.setId(id);
        supplierRepository.update(supplier);
    }

    @Override
    public void deleteSupplierById(Integer id) {
        supplierRepository.delete(id);
    }

    @Override
    public Supplier loadSupplierById(Integer id) {
        return supplierRepository.selectProductSupplier(id);
    }

}
