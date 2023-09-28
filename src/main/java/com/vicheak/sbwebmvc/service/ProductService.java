package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.util.SlugUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(Product.builder()
                .id(1)
                .name("Dell Desktop")
                .slug(SlugUtil.toSlug("Dell Desktop"))
                .price(100.00)
                .inStock(true)
                .build());

        products.add(Product.builder()
                .id(2)
                .name("Asus Laptop")
                .slug(SlugUtil.toSlug("Asus Laptop"))
                .price(960.00)
                .inStock(true)
                .build());

        products.add(Product.builder()
                .id(3)
                .name("Mac Book M1 Pro")
                .slug(SlugUtil.toSlug("Mac Book M1 Pro"))
                .price(2000.00)
                .inStock(true)
                .build());

        products.add(Product.builder()
                .id(4)
                .name("Magic Mouse 2")
                .slug(SlugUtil.toSlug("Magic Mouse 2"))
                .price(180.00)
                .inStock(false)
                .build());
    }

    public List<Product> loadProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        if (product != null) {
            product.setSlug(SlugUtil.toSlug(product.getName()));
            products.add(product);
            return product;
        }
        throw new RuntimeException("Product cannot be added");
    }

    public Product updateProduct(Integer id, Product newProduct) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .peek(product -> {
                    product.setName(newProduct.getName());
                    product.setSlug(SlugUtil.toSlug(newProduct.getName()));
                    product.setPrice(newProduct.getPrice());
                    product.setInStock(newProduct.getInStock());
                })
                .findFirst()
                .orElseThrow();
    }

    public Product loadProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Product is not found!")
                );
    }

    public List<Product> searchProduct(String name, Boolean status) {
        return products.stream()
                .filter(product ->
                        product.getName().toLowerCase().contains(name.toLowerCase())
                                && product.getInStock().equals(status))
                .toList();
    }

    public void deleteProductById(Integer id) {
        products = products.stream()
                .filter(product -> !product.getId().equals(id))
                .collect(Collectors.toList());
    }

}
