package com.netpos.desafiobackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Stock stock;

    @ManyToOne
    private UserAccount userAccount;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(Integer id, String code, String name, Float price, Stock stock, UserAccount userAccount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.userAccount = userAccount;
    }

    public static class Builder {
        private Integer id;
        private String code;
        private String name;
        private Float price;
        private Stock stock;
        private UserAccount userAccount;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Float price) {
            this.price = price;
            return this;
        }

        public Builder stock(Stock stock) {
            this.stock = stock;
            return this;
        }

        public Builder userAccount(UserAccount userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public Product build() {
            return new Product(id, code, name, price, stock, userAccount);
        }
    }
}
