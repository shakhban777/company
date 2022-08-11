package com.company.controllers;

import com.company.API.model.ProductDto;
import com.company.domain.models.Product;
import com.company.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

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
        productService.addProductList(file);
        return "home";
    }

    public String getAll(Model model) {
        log.info(productService.getProductList().toString());
        return "home";
    }

    public String getByCipher(String cipher, Model model) {
        return null;
    }

    public String delete(String cipher, Model model) {
        return null;
    }

    @GetMapping ("/get/{cipher}")//тестирую метод по получению продукта
    public String getByCipher(@PathVariable String cipher) {
        productService.getProduct(cipher);
        return "home";
    }

    @PostMapping("/add2")
    public String add(@RequestBody ProductDto newProduct) {
        log.info("method add: " + newProduct.toString());
        productService.addProduct(newProduct);
        return "home";
    }
}
