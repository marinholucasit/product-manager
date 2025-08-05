package br.com.lm.gateway;

import br.com.lm.entity.Product;
import br.com.lm.model.PaginatedProducts;

import java.util.List;

public interface ProductGateway {
    boolean existsByProductAndType(String product, String type);
    Product save(Product product);
    Product findByName(String name);
    PaginatedProducts findByProductContainingAndPriceBetween(
            String product, Double minPrice, Double maxPrice, int page, int size);
    void saveAll(List<Product> products);
}
