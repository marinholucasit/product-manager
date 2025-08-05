package br.com.lm.usecaseimpl;

import br.com.lm.entity.Product;
import br.com.lm.exception.ProductAlreadyExistsException;
import br.com.lm.gateway.ProductGateway;
import br.com.lm.usecase.CreateProductUseCase;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductGateway productGateway;

    public CreateProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product createProduct(Product product) {

        if (product.getProduct() == null || product.getProduct().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        if (productGateway.existsByProductAndType(product.getProduct(), product.getType())) {
            String errorMessage = String.format("Product with name %s and type %s already exists",
                    product.getProduct(), product.getType());
            throw new ProductAlreadyExistsException(errorMessage);
        }

        Product createdProduct = productGateway.save(product);

        return createdProduct;
    }
}
