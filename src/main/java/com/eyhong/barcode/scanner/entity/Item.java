package com.eyhong.barcode.scanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    private String name;
    @Column(unique = true)
    private String barcode;
    private Integer quantity;
    private Double price;
}
