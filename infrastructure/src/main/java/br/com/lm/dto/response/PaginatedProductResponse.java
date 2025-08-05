package br.com.lm.dto.response;

import lombok.Builder;
import lombok.Getter;
import br.com.lm.model.PaginatedProducts;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PaginatedProductResponse {
    private List<ProductResponseDTO> products;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public static PaginatedProductResponse fromDomain(PaginatedProducts paginated) {
        List<ProductResponseDTO> products = paginated.getProducts().stream()
                .map(ProductResponseDTO::fromDomain)
                .collect(Collectors.toList());

        return PaginatedProductResponse.builder()
                .products(products)
                .currentPage(paginated.getCurrentPage())
                .totalPages(paginated.getTotalPages())
                .totalItems(paginated.getTotalItems())
                .build();
    }
}
