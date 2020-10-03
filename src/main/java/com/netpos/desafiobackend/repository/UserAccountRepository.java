package com.netpos.desafiobackend.repository;

import com.netpos.desafiobackend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}
