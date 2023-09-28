package com.vicheak.sbwebmvc.controller;

import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//front controller notices this controller
@RestController //all return type is serialized accordingly to JSON data format
@RequestMapping("/products") //group as class level
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //this is a mapping url with get method
    //@RequestMapping(method = RequestMethod.GET, value = "/products")
    //method attribute : used with type safe

    //@GetMapping("/products")
    //@GetMapping
    @GetMapping(produces = "application/json")
    public List<Product> getProducts() {
        //return "Retrieve Products";
        return productService.loadProducts();
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        //return "Create new product";
        //System.out.println(product);

        /*Product newProduct = Product.builder()
                .id(5)
                .name("Gaming Chair 2023")
                .price(180.00)
                .inStock(true)
                .build();*/
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product newProduct) {
        //return "Update product";

        /*Product newProduct = Product.builder()
                .id(4)
                .name("Magic Mouse 2")
                .price(210.99)
                .inStock(true)
                .build();*/
        return productService.updateProduct(id, newProduct);
    }

    @PatchMapping
    public String updateProductPartially() {
        return "Update product partially";
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    //@GetMapping("/{id}") //create placeholder url for path variable (dynamic)
    @GetMapping("/{id}/{slug}")
    public Product getProductById(@PathVariable("id") Integer proId,
                                  @PathVariable String slug) {
        System.out.println("Product ID : " + proId);
        System.out.println("Product Slug : " + slug);
        return productService.loadProductById(proId);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam(required = false, defaultValue = "") String name, //@RequestParam("q") : set query string name
                                       @RequestParam(required = false, defaultValue = "true") Boolean status) {
        System.out.println("Name : " + name);
        System.out.println("Status : " + status);
        return productService.searchProduct(name, status);
    }

}
