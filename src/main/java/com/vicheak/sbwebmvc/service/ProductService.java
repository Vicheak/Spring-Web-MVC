package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> loadProducts();

    void createNewProduct(Product product);

    void updateProductById(Integer id, Product product);

    void deleteProductById(Integer id);

    Product loadProductById(Integer id);

}
