package com.example.demo.model.order;

import java.util.stream.Stream;

public enum OrderStatus {
    NEW("NEW"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED");

    private String name;

    public String getName() {
        return name;
    }

    public static OrderStatus getByName(String name) {
        return Stream.of(values()).filter(c -> c.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    OrderStatus(String name) {
        this.name = name;
    }
}
