package com.technicaltest.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductData {

    private Long id;

    private String name;

    private String sku;

    private String barcode;

    private String image;

    private String price;

    private Boolean enabled;
}
