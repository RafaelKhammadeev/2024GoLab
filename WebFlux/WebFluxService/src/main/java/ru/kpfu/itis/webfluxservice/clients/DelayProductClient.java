package ru.kpfu.itis.webfluxservice.clients;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.kpfu.itis.webfluxservice.models.Product;

/**
 * @author Ilya Ivlev
 */

@Component
public class DelayProductClient implements Client {
  private final WebClient client;

  public DelayProductClient(@Value("${delayProduct.url}") String url) {
    client = WebClient.builder()
        .baseUrl(url)
        .build();
  }

  @Override
  public Flux<Product> getAll() {
    return client.get()
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMap(clientResponse -> clientResponse.bodyToMono(Product[].class))
        .flatMapIterable(Arrays::asList);
  }
}
