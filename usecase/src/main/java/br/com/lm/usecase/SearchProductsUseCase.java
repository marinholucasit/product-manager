package br.com.lm.usecase;

import br.com.lm.model.PaginatedProducts;

public interface SearchProductsUseCase {
    PaginatedProducts searchProducts(String productName, Double minPrice, Double maxPrice, int page, int size);
}
