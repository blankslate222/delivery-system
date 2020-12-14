package com.nikhil.delivery.system.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Order {
    private String id;
    private String name;
    private long prepTimeMs;
}
