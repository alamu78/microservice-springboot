package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @GetMapping
  public List<Map<String,Object>> all() {
    // include instance port in name to see which instance served the request
    String instanceInfo = System.getProperty("server.port", "unknown");
    return List.of(
      Map.of("id", 1, "name", "Laptop from port:"+instanceInfo),
      Map.of("id", 2, "name", "Phone from port:"+instanceInfo)
    );
  }

  @GetMapping("/{id}")
  public Map<String,Object> get(@PathVariable int id) {
    String instanceInfo = System.getProperty("server.port", "unknown");
    return Map.of("id", id, "name", "Product-"+id+" from port:"+instanceInfo);
  }
}
