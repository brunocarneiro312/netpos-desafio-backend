package com.netpos.desafiobackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "QUANTITY", columnDefinition = "integer default 0", length = 1000)
    private Integer quantity;

}
