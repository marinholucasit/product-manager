package br.com.lm.dto.response;

import br.com.lm.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {
    private String product;
    private String type;
    private Integer quantity;
    private Double price;
    private String industry;
    private String origin;

    public static ProductResponseDTO fromDomain(Product product) {
        return ProductResponseDTO.builder()
                .product(product.getProduct())
                .type(product.getType())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .industry(product.getIndustry())
                .origin(product.getOrigin())
                .build();
    }
}
