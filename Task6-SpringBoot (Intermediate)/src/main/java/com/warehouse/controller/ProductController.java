package com.warehouse.controller;

import com.warehouse.dto.ProductDTO;
import com.warehouse.enums.ProductType;
import com.warehouse.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Add Product
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(service.add(dto), HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAll());
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Search by name
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }

    // Get by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductDTO>> getByType(@PathVariable ProductType type) {
        return ResponseEntity.ok(service.getByType(type));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
