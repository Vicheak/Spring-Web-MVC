package com.vicheak.sbwebmvc.repository;

import com.vicheak.sbwebmvc.model.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SupplierRepository {

    //method for mapping the relationship to product
    @Select("SELECT * FROM suppliers WHERE id = #{id}")
    Supplier selectProductSupplier(@Param("id") Integer id);

    @Select("SELECT * FROM suppliers")
    @Results(id = "supplierResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company"),
            @Result(property = "since", column = "since"),
            @Result(property = "status", column = "status")
    })
    List<Supplier> select();

    @Insert("""
                INSERT INTO suppliers (company, since, status)
                VALUES (#{s.company}, #{s.since}, #{s.status})
            """)
    void insert(@Param("s") Supplier supplier);

    @Update("""
                UPDATE suppliers 
                SET company = #{s.company}, 
                status = #{s.status}
                WHERE id = #{s.id}
            """)
    void update(@Param("s") Supplier supplier);

    @Delete("""
                DELETE FROM suppliers WHERE id = #{id}
            """)
    void delete(@Param("id") Integer id);

}
