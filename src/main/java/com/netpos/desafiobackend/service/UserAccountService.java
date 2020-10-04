package com.netpos.desafiobackend.service;

import com.netpos.desafiobackend.entity.UserAccount;
import com.netpos.desafiobackend.error.GenericError;

import java.util.List;

public interface UserAccountService extends CrudService<UserAccount> {

    List<UserAccount> filterByName(String name) throws GenericError;

}
