package com.example.demo.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

  @GetMapping("/products")
  List<Map<String,Object>> getProducts();

  @GetMapping("/products/{id}")
  Map<String,Object> getProductById(@PathVariable("id") int id);
}