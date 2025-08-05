package usecase;

import model.PaginatedProducts;

import java.awt.print.Pageable;

public interface SearchProductsUseCase {
    PaginatedProducts searchProducts(String productName, Double minPrice, Double maxPrice, int page, int size);
}
