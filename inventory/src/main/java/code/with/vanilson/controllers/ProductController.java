package code.with.vanilson.controllers;

import code.with.vanilson.dto.ProductDTO;
import code.with.vanilson.persistence.model.Product;
import code.with.vanilson.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/product")
    public ResponseEntity<List<Product>> listAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping(value = "/product/create")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productDTO));
    }
}
