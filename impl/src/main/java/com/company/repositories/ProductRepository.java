package com.company.repositories;

import com.company.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product getProductByCipher (String cipher);

    void deleteProductByCipher (String cipher);

    Product findByCipher(String cipher);
}