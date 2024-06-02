package ru.kpfu.itis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ilya Ivlev
 */

@Builder
@Data
@AllArgsConstructor
public class Product {
  private String name;

  private double price;

  private String description;

}
