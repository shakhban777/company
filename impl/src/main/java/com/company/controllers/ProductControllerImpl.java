package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.awt.print.Pageable;

@Slf4j
@Controller
public class ProductControllerImpl implements com.company.API.controllers.resources.ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    public String add(@Valid ProductDto newProduct, Model model, Errors errors) {
        log.info("method add: " + newProduct.toString());
        productService.addProduct(newProduct);
        return "home";
    }

    public String addList(MultipartFile file, Model model) {
        return null;
    }

    public String getAll(Model model, Pageable pageable) {
        return null;
    }

    public String getByCipher(String cipher, Model model) {
        return null;
    }

    public String delete(String cipher, Model model) {
        return null;
    }
}