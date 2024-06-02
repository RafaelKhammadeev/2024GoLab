package ru.kpfu.itis.webfluxservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.kpfu.itis.webfluxservice.models.Product;
import ru.kpfu.itis.webfluxservice.services.Service;

/**
 * @author Ilya Ivlev
 */

@AllArgsConstructor
@RestController
public class Controller {
  private final Service service;

  @GetMapping("/all")
  public Flux<Product> getAll() {
    return service.getAll();
  }
}
