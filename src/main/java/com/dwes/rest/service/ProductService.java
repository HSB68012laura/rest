package com.dwes.rest.service;

import com.dwes.rest.error.ProductNotFoundException;
import com.dwes.rest.model.Product;
import com.dwes.rest.model.ProductJpaRepository;
import com.dwes.rest.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    //private final ProductRepository productRepository;
    private final ProductJpaRepository productRepository;

    public List<Product> getAll() {
        List<Product> result = productRepository.findAll(); //productRepository.getAll();
        if (result.isEmpty())
            throw new ProductNotFoundException();
        return result;
    }

    public List<Product> query(double maxPrice, String sortDirection) {
       String normalizedSortDirection = sortDirection.equalsIgnoreCase("desc") ? "DESC" : "ASC";
        Sort sort = Sort.by(Sort.Direction.valueOf(normalizedSortDirection), "name");
        List<Product> result = productRepository.query(maxPrice, sort);
        if (result.isEmpty())
            throw new ProductNotFoundException();
        return result;
    }

    public Product get(Long id) {
        return productRepository. findById(id)//get(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product add(Product product) {
        return productRepository.save(product); //add(product);

    }

    public Product edit(Long id, Product newValue) {
        /*return productRepository.edit(id, newValue)
                .orElseThrow(() -> new ProductNotFoundException(id));*/
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(newValue.getName());
                    p.setPrice(newValue.getPrice());
                    return productRepository.save(p);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(Long id) {
        //productRepository.delete(id);
        productRepository.deleteById(id);
    }
}
