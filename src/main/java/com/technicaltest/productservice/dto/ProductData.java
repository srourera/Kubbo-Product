package com.technicaltest.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductData {

    private Long id;

    private String name;

    private String sku;

    private String barcode;

    private Long image;

    private String price;

    private Boolean enabled;
}
