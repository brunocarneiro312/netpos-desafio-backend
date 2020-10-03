package com.netpos.desafiobackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {

    private Integer id;

    private String code;

    private String name;

    private Float price;

    private Stock stock;
}
