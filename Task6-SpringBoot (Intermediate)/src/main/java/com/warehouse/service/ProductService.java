package com.warehouse.service;

import com.warehouse.dto.ProductDTO;
import com.warehouse.entity.Product;
import com.warehouse.enums.ProductType;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.mapper.ProductMapper;
import com.warehouse.repository.ProductRepository;
import com.warehouse.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // Add
    public ProductDTO add(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        ValidationUtil.validate(product);
        return ProductMapper.toDTO(repo.save(product));
    }

    // Get All
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    // Get by id
    @Transactional(readOnly = true)
    public ProductDTO getById(int id) {
        Product product = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toDTO(product);
    }

    // Search by name
    @Transactional(readOnly = true)
    public List<ProductDTO> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    // Get by type
    @Transactional(readOnly = true)
    public List<ProductDTO> getByType(ProductType type) {
        return repo.findByProductType(type)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    // Update
    public ProductDTO update(int id, ProductDTO dto) {
        // verify exists first
        repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        dto.setId(id);
        Product product = ProductMapper.toEntity(dto);
        ValidationUtil.validate(product);
        return ProductMapper.toDTO(repo.save(product));
    }

    // Delete
    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
