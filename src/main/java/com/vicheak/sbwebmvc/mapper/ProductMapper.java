package com.vicheak.sbwebmvc.mapper;

import com.vicheak.sbwebmvc.dto.CreateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductDto;
import com.vicheak.sbwebmvc.dto.UpdateProductPartialDto;
import com.vicheak.sbwebmvc.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") //create implementation bean at compile time
public interface ProductMapper {

    //source - can be POJO or DTO
    //target - can be POJO or DTO
    //ex. mapCreateProductDtoToProduct
    //fromCreateProductDto (DTO -> POJO)
    //toProductDto (POJO -> DTO)

    @Mapping(source = "supplierId", target = "supplier.id")
    Product fromCreateProductDto(CreateProductDto createProductDto);

    @Mapping(source = "supplierId", target = "supplier.id")
    Product fromUpdateProductDto(UpdateProductDto updateProductDto);

    Product fromUpdateProductPartialDto(UpdateProductPartialDto updateProductPartialDto);

}
