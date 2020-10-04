package com.netpos.desafiobackend.repository;

import com.netpos.desafiobackend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    /**
     * Filtra os usuários pelo nome
     */
    List<UserAccount> findByFullNameLike(String name);
}
