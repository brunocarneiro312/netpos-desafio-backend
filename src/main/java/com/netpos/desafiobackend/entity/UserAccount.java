package com.netpos.desafiobackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {

    public UserAccount() {

    }

    public UserAccount(Integer id, String email, String fullName, String senha) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.senha = senha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String fullName;
        private String email;
        private String senha;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public UserAccount build() {
            return new UserAccount(id, fullName, email, senha);
        }
    }
}
