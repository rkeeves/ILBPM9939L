package io.github.rkeeves.example;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InventoryItemExample {

    String name, price;
}
