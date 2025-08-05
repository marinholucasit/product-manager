package br.com.lm.mapper;

import br.com.lm.entity.ProductEntity;
import br.com.lm.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity toEntity(Product product);
    Product toDomain(ProductEntity entity);
}
