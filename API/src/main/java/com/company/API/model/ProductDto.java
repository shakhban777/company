package com.company.API.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDto {

    @NotBlank(message = "поле не заполнено")
    private String name;

    @NotBlank(message = "поле не заполнено")
    private String cipher;

    private ProductDto product;
}
