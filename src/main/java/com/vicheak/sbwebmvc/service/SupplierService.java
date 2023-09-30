package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> loadSuppliers();

    void addNewSupplier(Supplier supplier);

    void updateSupplierById(Integer id, Supplier supplier);

    void deleteSupplierById(Integer id);

    Supplier loadSupplierById(Integer id);

}
