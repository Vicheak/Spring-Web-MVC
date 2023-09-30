package com.vicheak.sbwebmvc.repository.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

//for dynamic sql and complex sql, statistics, reports
public class CategoryProvider implements ProviderMethodResolver { //match the provider's name and repo's name

    private final String TB_CATEGORY = "categories";

    public String selectProductCategories() {
        return new SQL() {{
            SELECT("*");
            FROM(TB_CATEGORY + " c");
            INNER_JOIN("products_categories pc ON pc.category_id = c.id");
            WHERE("pc.product_id = #{productId}");
        }}.toString();
    }

}
