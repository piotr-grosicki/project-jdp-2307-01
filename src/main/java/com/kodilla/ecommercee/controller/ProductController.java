package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductNotFoundException;
import com.kodilla.ecommercee.exception.GroupProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductDbService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productsDto) throws GroupProductNotFoundException {
        productService.saveProduct(productMapper.mapToProduct(productsDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productService.getAllProducts()));
    }

    @GetMapping(value = "{productsId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productsId) throws  ProductNotFoundException{
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProduct(productsId).orElseThrow(() -> new ProductNotFoundException(productsId))));
    }

    @DeleteMapping(value = "{productsId}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long productsId){
        productService.deleteProduct(productsId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productsDto) throws GroupProductNotFoundException, ProductNotFoundException{
        Product product = productMapper.mapToProduct(productsDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }
}