package com.vicheak.sbwebmvc.service.impl;

import com.vicheak.sbwebmvc.dto.CreateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductPartialDto;
import com.vicheak.sbwebmvc.mapper.ProductMapper;
import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.repository.ProductRepository;
import com.vicheak.sbwebmvc.service.ProductService;
import com.vicheak.sbwebmvc.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> loadProducts() {
        return productRepository.select();
    }

    @Transactional
    @Override
    public void createNewProduct(CreateProductDto createProductDto) {
        //Map DTO to POJO
        Product product = productMapper.fromCreateProductDto(createProductDto);
        product.setSlug(SlugUtil.toSlug(createProductDto.name()));

        //Start inserting a product
        productRepository.insert(product);

        //System.out.println("INSERT PRODUCT ID : " + product.getId());

        //Start inserting a product category
        createProductDto.categoryIds().forEach(id ->
                productRepository.insertProductCategories(product.getId(), id));
    }

    @Transactional
    @Override
    public void updateProductById(Integer id, UpdateProductDto updateProductDto) {
        //Map DTO to POJO
        Product product = productMapper.fromUpdateProductDto(updateProductDto);
        product.setId(id);

        if (product.getName() != null) {
            product.setSlug(SlugUtil.toSlug(updateProductDto.name()));
        }

        productRepository.update(product);

        //reset product categories);
        productRepository.deleteProductCategories(product.getId());

        //Start inserting a product category
        updateProductDto.categoryIds().forEach(catId ->
                productRepository.insertProductCategories(product.getId(), catId));
    }

    @Transactional
    @Override
    public void updateProductPartially(Integer id, UpdateProductPartialDto updateProductPartialDto) {
        //update only product price and inStock
        Product product = productMapper.fromUpdateProductPartialDto(updateProductPartialDto);
        product.setId(id);

        productRepository.updatePartially(product);
    }

    @Transactional
    @Override
    public void deleteProductById(Integer id) {
        //Start deleting from product category
        productRepository.deleteProductCategories(id);

        //Start deleting from product
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
