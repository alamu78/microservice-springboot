package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final ProductClient productClient;

  public OrderController(ProductClient productClient) {
    this.productClient = productClient;
  }

  @GetMapping("/products")
  @CircuitBreaker(name = "productService", fallbackMethod = "productsFallback")
  public List<Map<String,Object>> getProducts() {
    return productClient.getProducts();
  }

  public List<Map<String,Object>> productsFallback(Throwable ex) {
    return List.of(Map.of("id", -1, "name", "default-product (fallback)"));
  }

  @GetMapping("/products/{id}")
  @CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
  public Map<String,Object> getProduct(@PathVariable int id) {
    return productClient.getProductById(id);
  }

  public Map<String,Object> productFallback(int id, Throwable ex) {
    return Map.of("id", id, "name", "fallback-product");
  }
}
