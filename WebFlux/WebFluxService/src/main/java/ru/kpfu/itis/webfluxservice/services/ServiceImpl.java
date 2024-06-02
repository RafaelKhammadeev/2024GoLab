package ru.kpfu.itis.webfluxservice.services;

import java.util.List;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.kpfu.itis.webfluxservice.clients.Client;
import ru.kpfu.itis.webfluxservice.models.Product;

/**
 * @author Ilya Ivlev
 */

@Component
public class ServiceImpl implements Service {
  private final List<Client> clients;

  public ServiceImpl(List<Client> clients) {
    this.clients = clients;
  }

  @Override
  public Flux<Product> getAll() {
    List<Flux<Product>> fluxes = clients.stream().map(this::getAll).toList();
    return Flux.merge(fluxes);
  }

  private Flux<Product> getAll(Client client) {
    return client.getAll()
        .subscribeOn(Schedulers.boundedElastic());
  }

}
