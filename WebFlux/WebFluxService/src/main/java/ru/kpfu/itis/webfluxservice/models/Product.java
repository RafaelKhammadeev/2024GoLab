package ru.kpfu.itis.webfluxservice.models;

/**
 * @author Ilya Ivlev
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Product {
  private String name;

  private double price;

  private String description;

}
