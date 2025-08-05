package br.com.lm.config;

import lombok.extern.slf4j.Slf4j;
import br.com.lm.model.ProductModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import br.com.lm.usecase.ImportProductsUseCase;
import br.com.lm.utils.JsonProductReader;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ProductDataInitializer {

    private final JsonProductReader jsonProductReader;
    private final ImportProductsUseCase importProductsUseCase;


    public ProductDataInitializer(JsonProductReader jsonProductReader,
                                  ImportProductsUseCase importProductsUseCase) {
        this.jsonProductReader = jsonProductReader;
        this.importProductsUseCase = importProductsUseCase;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeProductData() {
        try {

            List<ProductModel> allProducts = jsonProductReader.readProductsFromJsonFiles()
                    .stream()
                    .filter(Objects::nonNull)
                    .toList();

            if (allProducts.isEmpty()) {
                log.warn("No products found in JSON files");
                return;
            }

            log.info("Total products found in all files: {}", allProducts.size());
            importProductsUseCase.importProducts(allProducts);
            log.info("Product loading completed successfully");

        } catch (Exception e) {
            log.error("Error in initial product loading", e);
            throw new RuntimeException("Error in initial product loading", e);
        }
    }
}
