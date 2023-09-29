package com.vicheak.sbwebmvc.repository;

import com.vicheak.sbwebmvc.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper //this repo is the mapper of MyBatis
@Repository
public interface ProductRepository {

    @Select("SELECT * FROM products")
    List<Product> select();

    @Select("SELECT * FROM products WHERE id = #{id}")
    Optional<Product> selectById(@Param("id") Integer id); //can be used with @Param

}
