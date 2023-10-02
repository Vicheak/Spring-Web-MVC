package com.vicheak.sbwebmvc.service.impl;

import com.vicheak.sbwebmvc.dto.CreateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductPartialDto;
import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.model.Supplier;
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

    @Override
    public List<Product> loadProducts() {
        return productRepository.select();
    }

    @Transactional
    @Override
    public void createNewProduct(CreateProductDto createProductDto) {
        //Map DTO to POJO
        Product product = Product.builder()
                .name(createProductDto.name())
                .slug(SlugUtil.toSlug(createProductDto.name()))
                .description(createProductDto.description())
                .price(createProductDto.price())
                .inStock(createProductDto.inStock())
                .supplier(Supplier.builder()
                        .id(createProductDto.supplierId())
                        .build())
                .build();

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
        Product product = Product.builder()
                .id(id)
                .name(updateProductDto.name())
                .slug(SlugUtil.toSlug(updateProductDto.name()))
                .description(updateProductDto.description())
                .supplier(Supplier.builder()
                        .id(updateProductDto.supplierId())
                        .build())
                .build();

        List<Integer> productCategoryIds = productRepository.selectProductCategoryIds(id);

        if (updateProductDto.categoryIds().size() == productCategoryIds.size()) {
            //Start updating the product
            productRepository.update(product);

            //Start updating the product category
            for (int i = 0; i < productCategoryIds.size(); i++) {
                productRepository.updateProductCategories(productCategoryIds.get(i),
                        updateProductDto.categoryIds().get(i));
            }

            /*System.out.println("to update : ");
            System.out.println(updateProductDto.categoryIds());
            System.out.println("product category ids : ");
            System.out.println(productCategoryIds);*/
        }
    }

    @Transactional
    @Override
    public void updateProductPartially(Integer id, UpdateProductPartialDto updateProductPartialDto) {
        //update only product price and inStock

        Product product = Product.builder()
                .id(id)
                .price(updateProductPartialDto.price())
                .inStock(updateProductPartialDto.inStock())
                .build();

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
