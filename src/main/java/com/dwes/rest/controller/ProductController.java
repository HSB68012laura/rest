package com.dwes.rest.controller;

import com.dwes.rest.model.Product;
import com.dwes.rest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
//@CrossOrigin(originPatterns = "http://localhost:[*]",
        //methods = RequestMethod.GET)
//@Tag(name = "Products", description = "Gestión básica de productos")
public class ProductController {

    private final ProductService productService;

    //@CrossOrigin(origins ="http://localhost:9000")
    @GetMapping
    public List<Product> getAll(
            @RequestParam(required = false, value = "maxPrice", defaultValue = "99999999") double max,
            @RequestParam(required = false, value = "sort", defaultValue = "asc") String sortDirection
    ) {
        return productService.query(max, sortDirection);
    }

    /*@Operation(
            summary = "Obtener un producto concreto",
            description = "Permite obtener la información de un producto si se le proporciona un id",
            tags = {"params", "products", "detail"}
    )
    @ApiResponse(description = "Información detallada del producto",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject("""
                                {
                                    "id": 3,
                                    "name": "Headphones",
                                    "price": 150.0
                                }
                                """)
                    }
            )
    )
    @GetMapping("/{id:[0-9]+}")
    public Product getById(@Parameter(description = "Identificador del producto") @PathVariable Long id) {
        return productService.get(id);
    }*/
    @GetMapping("/{id:[0-9]+}")
    public Product getById(@PathVariable long id) {
        return productService.get(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(
           /* @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Producto a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class),
                            examples = @ExampleObject("""
                                {
                                    "name": "New product",
                                    "price": 123.45
                                }
                                """)
                    )
            )*/
            @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.add(product));
    }

    @PutMapping("/{id}")
    public Product edit(
            @RequestBody Product product,
            @PathVariable("id") Long productId) {

        return productService.edit(productId, product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
