package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.dto.CreateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductPartialDto;
import com.vicheak.sbwebmvc.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> loadProducts();

    void createNewProduct(CreateProductDto createProductDto);

    void updateProductById(Integer id, UpdateProductDto updateProductDto);

    void updateProductPartially(Integer id, UpdateProductPartialDto updateProductPartialDto);

    void deleteProductById(Integer id);

    Product loadProductById(Integer id);

    List<Product> searchProduct(String name, Boolean status);

}
