package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public void createNewProduct(Product product) {

    }

    @Override
    public void deleteProductById(Integer id) {

    }

    @Override
    public void updateProductById(Integer id, Product product) {

    }

    @Override
    public List<Product> loadProducts() {
        return null;
    }

    @Override
    public Product loadProductById(Integer id) {
        return null;
    }
}
