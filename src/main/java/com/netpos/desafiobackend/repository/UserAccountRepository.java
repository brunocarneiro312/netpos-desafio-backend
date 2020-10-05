package com.netpos.desafiobackend.repository;

import com.netpos.desafiobackend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author brunocarneiro
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    /**
     * Filtra os usuários pelo nome
     */
    List<UserAccount> findByFullNameStartingWith(String name);
}
