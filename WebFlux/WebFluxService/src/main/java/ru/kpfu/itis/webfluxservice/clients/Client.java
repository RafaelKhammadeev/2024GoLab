package ru.kpfu.itis.webfluxservice.clients;

import reactor.core.publisher.Flux;
import ru.kpfu.itis.webfluxservice.models.Product;

/**
 * @author Ilya Ivlev
 */
public interface Client {
  Flux<Product> getAll();
}
