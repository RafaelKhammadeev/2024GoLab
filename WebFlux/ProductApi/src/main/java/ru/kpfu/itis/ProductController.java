package ru.kpfu.itis;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilya Ivlev
 */

@RestController
public class ProductController {

  @GetMapping("/products")
  public List<Product> products() {

    return List.of(
        Product
            .builder()
            .name("Cola")
            .price(50)
            .description("Cold and tasty")
            .build(),
        Product
            .builder()
            .name("Ice cream")
            .price(30)
            .description("chocolate")
            .build());
  }
}
