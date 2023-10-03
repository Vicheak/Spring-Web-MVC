package com.vicheak.sbwebmvc.controller;

import com.vicheak.sbwebmvc.dto.CreateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductPartialDto;
import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.loadProducts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNewProduct(@RequestBody CreateProductDto createProductDto) {
        productService.createNewProduct(createProductDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id,
                              @RequestBody UpdateProductDto updateProductDto) {
        productService.updateProductById(id, updateProductDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateProductPartially(@PathVariable Integer id,
                                       @RequestBody UpdateProductPartialDto updateProductPartialDto) {
        productService.updateProductPartially(id, updateProductPartialDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer proId) {
        return productService.loadProductById(proId);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam(required = false, defaultValue = "") String name,
                                       @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return productService.searchProduct(name, status);
    }

}
