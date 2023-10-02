package com.vicheak.sbwebmvc.repository;

import com.vicheak.sbwebmvc.model.Product;
import com.vicheak.sbwebmvc.repository.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper //this repo is the mapper of MyBatis
@Repository
public interface ProductRepository {

    @Select("SELECT * FROM products ORDER BY id DESC")
    //@Result(property = "inStock", column = "in_stock")
    @Results(id = "productResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "inStock", column = "in_stock"),
            @Result(property = "categories", column = "id",
                    many = @Many(select = "com.vicheak.sbwebmvc.repository.CategoryRepository.selectProductCategories")),
            @Result(property = "supplier", column = "supplier_id", //foreign key
                    one = @One(select = "com.vicheak.sbwebmvc.repository.SupplierRepository.selectProductSupplier")) //map relationship
    })
    List<Product> select();

    @Select("SELECT * FROM products WHERE id = #{id}")
    //@Result(property = "inStock", column = "in_stock")
    @ResultMap("productResultMap")
    Optional<Product> selectById(@Param("id") Integer id); //can be used with @Param

    /*@Insert("""
                INSERT INTO products (name, slug, description, price, in_stock, supplier_id)
                VALUES (#{pro.name}, #{pro.slug}, #{pro.name}, #{pro.price}, #{pro.inStock}, #{pro.supplier.id})
            """)*/
    @InsertProvider(ProductProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(@Param("pro") Product product);

    @InsertProvider(ProductProvider.class)
    void insertProductCategories(@Param("proId") Integer productId,
                                 @Param("catId") Integer categoryId);

    /*@Update("""
                UPDATE products
                SET name = #{pro.name},
                slug = #{pro.slug},
                description = #{pro.name},
                price = #{pro.price},
                in_stock = #{pro.inStock},
                supplier_id = #{pro.supplier.id}
                WHERE id = #{pro.id}
            """)*/
    @UpdateProvider(ProductProvider.class)
    void update(@Param("pro") Product product);

    @UpdateProvider(ProductProvider.class)
    void updateProductCategories(@Param("id") Integer id,
                                 @Param("catId") Integer categoryId);

    @UpdateProvider(ProductProvider.class)
    void updatePartially(@Param("pro") Product product);

    @Delete(
            """
                        DELETE FROM products
                        WHERE id = #{id}                  
                    """)
    void delete(@Param("id") Integer id);

    @DeleteProvider(ProductProvider.class)
    void deleteProductCategories(@Param("proId") Integer productId);

    @SelectProvider(value = ProductProvider.class, method = "selectByQueryString")
    @ResultMap("productResultMap")
    List<Product> searchProduct(@Param("proName") String name, @Param("status") Boolean status);

    @SelectProvider(ProductProvider.class)
    List<Integer> selectProductCategoryIds(@Param("proId") Integer productId);

}
