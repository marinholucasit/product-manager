package br.com.lm.dto.request;

import entity.Product;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private String product;
    private int quantity;
    private String price;
    private String type;
    private String industry;
    private String origin;

    public Product toDomain() {
        return new Product(
                product,
                type,
                quantity,
                Double.parseDouble(price.replace("$", "")),
                industry,
                origin
        );
    }
}
