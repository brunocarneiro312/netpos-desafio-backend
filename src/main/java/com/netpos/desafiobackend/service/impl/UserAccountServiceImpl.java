package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.entity.UserAccount;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.UserAccountRepository;
import com.netpos.desafiobackend.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

/**
 * @author brunocarneiro
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount save(UserAccount userAccount) throws GenericError {
        return Optional.of(this.userAccountRepository.save(userAccount))
                .orElseThrow(GenericError::new);
    }

    @Override
    public UserAccount update(UserAccount userAccount) throws GenericError {
        /**
         * A especificação não solicita a implementação desse método
         * para o UserAccount
         */
        return null;
    }

    @Override
    public UserAccount delete(Integer id) throws GenericError {
        /**
         * A especificação não solicita a implementação desse método
         * para o UserAccount
         */
        return null;
    }

    @Override
    public UserAccount findById(Integer id) throws GenericError {
        return Optional.of(this.userAccountRepository.findById(id).get())
                .orElseThrow(GenericError::new);
    }

    @Override
    public List<UserAccount> findAll() throws GenericError {
        return Optional.of(this.userAccountRepository.findAll())
                .orElseThrow(GenericError::new);
    }

    @Override
    public List<UserAccount> filterByName(String name) throws GenericError {

        // Os acentos devem ser ignorados
        String nameSemAcentos = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return Optional.of(this.userAccountRepository.findByFullNameStartingWith(nameSemAcentos))
                .orElseThrow(GenericError::new);
    }
}
