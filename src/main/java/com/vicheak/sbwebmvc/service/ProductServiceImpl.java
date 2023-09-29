package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> loadProducts() {
        return productRepository.select();
    }

    @Override
    public void createNewProduct(Product product) {

    }

    @Override
    public void updateProductById(Integer id, Product product) {

    }

    @Override
    public void deleteProductById(Integer id) {

    }

    @Override
    public Product loadProductById(Integer id) {
        return productRepository.selectById(id).orElseThrow();
    }

}
