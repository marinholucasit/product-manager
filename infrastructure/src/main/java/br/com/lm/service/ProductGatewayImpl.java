package br.com.lm.service;

import br.com.lm.entity.ProductEntity;
import br.com.lm.entity.Product;
import br.com.lm.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import br.com.lm.mapper.ProductMapper;
import br.com.lm.model.PaginatedProducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import br.com.lm.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public boolean existsByProductAndType(String product, String type) {
        return repository.existsByProductAndType(product, type);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public PaginatedProducts findByProductContainingAndPriceBetween(
            String product, Double minPrice, Double maxPrice, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> pageResult = repository.findByProductContainingAndPriceBetween(
                product,
                minPrice != null ? minPrice : 0.0,
                maxPrice != null ? maxPrice : Double.MAX_VALUE,
                pageable);

        List<Product> products = pageResult.getContent().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());

        return new PaginatedProducts(
                products,
                pageResult.getNumber(),
                pageResult.getTotalPages(),
                pageResult.getTotalElements());
    }

    @Override
    public Product findByName(String name) {
        return repository.findByProduct(name)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NoSuchElementException("Product not found with name: " + name));
    }

    @Override
    public void saveAll(List<Product> products) {
        List<ProductEntity> entities = products.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        repository.saveAll(entities);
    }

}
