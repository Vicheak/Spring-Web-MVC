package com.vicheak.sbwebmvc.repository.provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ProductProvider implements ProviderMethodResolver {

    private final String TB_PRODUCT = "products";

    public String selectByQueryString() {
        return new SQL() {{
            SELECT("*");
            FROM(TB_PRODUCT);
            WHERE("name LIKE #{proName}");
            AND();
            WHERE("in_stock = #{status}");
        }}.toString();
    }

}
