package com.vicheak.sbwebmvc.repository.provider;

import com.vicheak.sbwebmvc.model.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ProductProvider implements ProviderMethodResolver {

    private final String TB_NAME = "products";

    public String selectByQueryString(@Param("proName") String name, @Param("status") Boolean status) {
        return new SQL() {{
            SELECT("*");
            FROM(TB_NAME);
            if(name != null)
                WHERE("name LIKE '%' || #{proName} || '%'");
            AND();
            if(status != null)
                WHERE("in_stock = #{status}");
        }}.toString();
    }

    public String insert() {
        return new SQL() {{
            INSERT_INTO(TB_NAME);
            VALUES("name", "#{pro.name}");
            VALUES("slug", "#{pro.slug}");
            VALUES("description", "#{pro.description}");
            VALUES("price", "#{pro.price}");
            VALUES("in_stock", "#{pro.inStock}");
            VALUES("supplier_id", "#{pro.supplier.id}");
        }}.toString();
    }

    public String insertProductCategories() {
        return new SQL() {{
            INSERT_INTO("products_categories");
            VALUES("product_id", "#{proId}");
            VALUES("category_id", "#{catId}");
        }}.toString();
    }

    public String update(@Param("pro") Product product) {
        return new SQL() {{
            UPDATE(TB_NAME);

            if (product.getName() != null && product.getSlug() != null) {
                SET("name = #{pro.name}");
                SET("slug = #{pro.slug}");
            }

            if (product.getDescription() != null)
                SET("description = #{pro.description}");

            if (product.getSupplier() != null)
                SET("supplier_id = #{pro.supplier.id}");

            WHERE("id = #{pro.id}");
        }}.toString();
    }

    public String updateProductCategories() {
        return new SQL() {{
            UPDATE("products_categories");
            SET("category_id = #{catId}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String updatePartially(@Param("pro") Product product) {
        return new SQL() {{
            UPDATE(TB_NAME);

            if(product.getPrice() != null)
                SET("price = #{pro.price}");

            if(product.getInStock() != null)
                SET("in_Stock = #{pro.inStock}");

            WHERE("id = #{pro.id}");
        }}.toString();
    }

    public String selectProductCategoryIds() {
        return new SQL() {{
            SELECT("id");
            FROM("products_categories");
            WHERE("product_id = #{proId}");
        }}.toString();
    }

    public String deleteProductCategories() {
        return new SQL() {{
            DELETE_FROM("products_categories");
            WHERE("product_id = #{proId}");
        }}.toString();
    }

}
