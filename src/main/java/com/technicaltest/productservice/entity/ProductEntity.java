
package com.technicaltest.productservice.entity;

import com.technicaltest.productservice.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String sku;

    private String barcode;

    private Long image;

    private String price;

    private Boolean enabled;

    @PrePersist
    public void prePersist() {
        this.id = null;
        if(Utils.isEmpty(this.enabled)) {
            this.enabled = false;
        }
    }
}