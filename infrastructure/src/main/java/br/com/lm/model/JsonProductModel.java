package br.com.lm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JsonProductModel implements ProductModel {
    String product;
    String type;
    Integer quantity;
    String price;
    String industry;
    String origin;

}