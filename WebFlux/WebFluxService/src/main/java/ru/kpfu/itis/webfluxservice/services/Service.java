package ru.kpfu.itis.webfluxservice.services;

import reactor.core.publisher.Flux;
import ru.kpfu.itis.webfluxservice.models.Product;

/**
 * @author Ilya Ivlev
 */
public interface Service {
  Flux<Product> getAll();
}
