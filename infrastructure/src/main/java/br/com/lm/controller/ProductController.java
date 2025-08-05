package br.com.lm.controller;

import br.com.lm.dto.request.ProductRequestDTO;
import br.com.lm.dto.response.PaginatedProductResponse;
import br.com.lm.dto.response.ProductResponseDTO;
import br.com.lm.entity.Product;
import lombok.RequiredArgsConstructor;
import br.com.lm.model.PaginatedProducts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.lm.usecase.CreateProductUseCase;
import br.com.lm.usecase.SearchProductsUseCase;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO request) {
        Product product = createProductUseCase.createProduct(request.toDomain());
        return ProductResponseDTO.fromDomain(product);
    }

    @GetMapping
    public ResponseEntity<PaginatedProductResponse> searchProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginatedProducts result = searchProductsUseCase.searchProducts(
                productName, minPrice, maxPrice, page, size);

        return ResponseEntity.ok(PaginatedProductResponse.fromDomain(result));
    }
}
