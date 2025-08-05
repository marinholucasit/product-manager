package br.com.lm.controller;

import br.com.lm.entity.Product;
import br.com.lm.model.PaginatedProducts;
import br.com.lm.usecase.CreateProductUseCase;
import br.com.lm.usecase.SearchProductsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @MockBean
    private SearchProductsUseCase searchProductsUseCase;

    @Test
    void whenCreateProduct_thenReturnCreated() throws Exception {
        Product product = new Product("Test", "T", 10, 5.0, "ind", "EUA");

        given(createProductUseCase.createProduct(any())).willReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product\":\"Test\",\"type\":\"T\",\"quantity\":10,\"price\":\"$5.00\",\"industry\":\"ind\",\"origin\":\"EUA\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.product").value("Test"))
                .andExpect(jsonPath("$.type").value("T"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(5.0))
                .andExpect(jsonPath("$.industry").value("ind"))
                .andExpect(jsonPath("$.origin").value("EUA"));
    }
}
