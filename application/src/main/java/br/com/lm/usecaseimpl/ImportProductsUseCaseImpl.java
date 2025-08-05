package br.com.lm.usecaseimpl;

import br.com.lm.entity.Product;
import br.com.lm.exception.InvalidProductException;
import br.com.lm.gateway.ProductGateway;
import br.com.lm.model.ProductModel;
import br.com.lm.usecase.ImportProductsUseCase;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImportProductsUseCaseImpl implements ImportProductsUseCase {
    private final ProductGateway productGateway;

    public ImportProductsUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public void importProducts(List<ProductModel> productModels) {

        List<Product> validProducts = productModels.stream()
                .map(this::convertToDomain)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        productGateway.saveAll(validProducts);
    }

    private Product convertToDomain(ProductModel model) {
        try {
            Product product = model.toDomain();
            validateProduct(product);
            return product;
        } catch (InvalidProductException e) {
            return null;
        }
    }

    private void validateProduct(Product product) throws InvalidProductException {
        if (product.getProduct() == null || product.getProduct().isEmpty()) {
            throw new InvalidProductException("Product name cannot be empty");
        }
        if (product.getType() == null || product.getType().isEmpty()) {
            throw new InvalidProductException("Product type cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidProductException("Product price must be positive");
        }
    }
}
