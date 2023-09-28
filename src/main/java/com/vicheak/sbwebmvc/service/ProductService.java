package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Product;

import java.util.List;

public interface ProductService {

    void createNewProduct(Product product);

    void deleteProductById(Integer id);

    void updateProductById(Integer id, Product product);

    List<Product> loadProducts();

    Product loadProductById(Integer id);

}
