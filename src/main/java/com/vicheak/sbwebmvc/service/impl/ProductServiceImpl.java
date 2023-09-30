package com.vicheak.sbwebmvc.service.impl;

import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.repository.ProductRepository;
import com.vicheak.sbwebmvc.service.ProductService;
import com.vicheak.sbwebmvc.util.SlugUtil;
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
        product.setSlug(SlugUtil.toSlug(product.getName()));
        product.setInStock(true);
        productRepository.insert(product);
    }

    @Override
    public void updateProductById(Integer id, Product product) {
        product.setId(id);
        product.setSlug(SlugUtil.toSlug(product.getName()));
        productRepository.update(product);
    }

    @Override
    public void updateProductPartially(Integer id, Product product) {
        product.setId(id);
        product.setSlug(SlugUtil.toSlug(product.getName()));
        productRepository.update(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.delete(id);
    }

    @Override
    public Product loadProductById(Integer id) {
        return productRepository.selectById(id).orElseThrow();
    }

    @Override
    public List<Product> searchProduct(String name, Boolean status) {
        return productRepository.searchProduct(name, status);
    }
}
