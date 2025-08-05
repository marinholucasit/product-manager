package usecaseimpl;

import br.com.lm.gateway.ProductGateway;
import br.com.lm.model.PaginatedProducts;
import br.com.lm.usecase.SearchProductsUseCase;

public class SearchProductsUseCaseImpl implements SearchProductsUseCase {
    private final ProductGateway productGateway;

    public SearchProductsUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public PaginatedProducts searchProducts(String productName, Double minPrice, Double maxPrice, int page, int size) {
        return productGateway.findByProductContainingAndPriceBetween(productName, minPrice, maxPrice, page, size);
    }
}
