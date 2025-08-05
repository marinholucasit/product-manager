package br.com.lm.config;

import br.com.lm.gateway.ProductGateway;
import br.com.lm.usecase.CreateProductUseCase;
import br.com.lm.usecase.ImportProductsUseCase;
import br.com.lm.usecase.SearchProductsUseCase;
import br.com.lm.usecaseimpl.CreateProductUseCaseImpl;
import br.com.lm.usecaseimpl.ImportProductsUseCaseImpl;
import br.com.lm.usecaseimpl.SearchProductsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeansConfig {
    @Bean
    public ImportProductsUseCase importProductsUseCase(ProductGateway productGateway) {
        return new ImportProductsUseCaseImpl(productGateway);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductGateway productGateway) {
        return new CreateProductUseCaseImpl(productGateway);
    }

    @Bean
    public SearchProductsUseCase searchProductsUseCase(ProductGateway productGateway) {
        return new SearchProductsUseCaseImpl(productGateway);
    }
}
