package com.vicheak.sbwebmvc.repository;

import com.vicheak.sbwebmvc.model.Category;
import com.vicheak.sbwebmvc.repository.provider.CategoryProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CategoryRepository {

    //using dynamic sql
    @SelectProvider(CategoryProvider.class)
    List<Category> selectProductCategories(@Param("productId") Integer productId);

    @Select("SELECT * FROM categories")
    @Results(id = "categoryResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description")
    })
    List<Category> select();

    @Select("SELECT * FROM categories WHERE id = #{id}")
    @ResultMap("categoryResultMap")
    Optional<Category> selectById(@Param("id") Integer id);

    @Insert("""
                INSERT INTO categories (name, description)
                VALUES (#{c.name}, #{c.description})
            """)
    void insert(@Param("c") Category category);

    @Update("""
                UPDATE categories 
                SET name = #{c.name}, 
                description = #{c.description}
                WHERE id = #{c.id}
            """)
    void update(@Param("c") Category category);

    @Delete("""
                DELETE FROM categories
                WHERE id = #{id}
            """)
    void delete(@Param("id") Integer id);

}
