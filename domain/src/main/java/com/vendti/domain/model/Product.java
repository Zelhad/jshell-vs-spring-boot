package com.vendti.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private String href;
    private Boolean isBundle;
}
