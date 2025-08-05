package br.com.lm.model;

import br.com.lm.entity.Product;

public interface ProductModel {
    String getProduct();
    String getType();
    Integer getQuantity();
    String getPrice();
    String getIndustry();
    String getOrigin();

    default Product toDomain() {
        return new Product(
                getProduct(),
                getType(),
                getQuantity(),
                Double.parseDouble(getPrice().replace("$", "")),
                getIndustry(),
                getOrigin()
        );
    }
}
