package br.com.lm.usecase;


import br.com.lm.model.ProductModel;

import java.util.List;

public interface ImportProductsUseCase {
    void importProducts(List<ProductModel> productModels);
}
