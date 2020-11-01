
package com.technicaltest.productservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "ProductImage")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    private byte[] data;

    public ImageEntity(){}

    public ImageEntity(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.data = picByte;
    }
}