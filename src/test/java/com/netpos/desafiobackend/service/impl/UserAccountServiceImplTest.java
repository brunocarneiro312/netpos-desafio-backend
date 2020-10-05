package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.entity.UserAccount;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.UserAccountRepository;
import com.netpos.desafiobackend.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * @author brunocarneiro
 */
@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

    private UserAccountService userAccountService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    public void setUp() {
        this.userAccountService = new UserAccountServiceImpl(userAccountRepository);
    }

    @Test
    @DisplayName("Quando for solicitado a listagem, então retorna lista de usuários.")
    public void whenGet_thenReturnUser() throws GenericError {

        // fixture
        given(this.userAccountRepository.findAll()).willReturn(Arrays.asList(getUser(), getUser()));

        // when
        List<UserAccount> userAccounts = this.userAccountService.findAll();

        // then
        assertAll("Get",
                () -> assertNotNull(userAccounts, "A lista não deveria estar nula"),
                () -> assertEquals(2, userAccounts.size(), "A lista deveria conter apenas 2 elementos."));

    }

    @Test
    @DisplayName("Dado um id de usuário, quando o id for buscado, então retorna usuário.")
    public void givenUserId_whenGet_thenReturnUser() throws GenericError {

        // given
        Integer id = 1;

        given(this.userAccountRepository.findById(id)).willReturn(Optional.of(this.getUser()));

        // when
        UserAccount recoveredUserAccount = this.userAccountService.findById(id);

        // then
        assertAll("FindById",
                () -> assertNotNull(recoveredUserAccount, "O usuário não deveria estar nulo"));
    }

    @Test
    @DisplayName("Dado um usuário, quando ele for salvo, então retorna usuário salvo.")
    public void givenUser_whenPost_thenReturnUser() throws GenericError {

        // given
        UserAccount userAccount = this.getUser();

        given(this.userAccountRepository.save(any(UserAccount.class))).willReturn(userAccount);

        // when
        UserAccount savedUserAccount = this.userAccountService.save(userAccount);

        // then
        assertAll("Save",
                () -> assertNotNull(savedUserAccount, "O usuário não deveria estar nulo"),
                () -> assertEquals(userAccount.toString(), savedUserAccount.toString(), "Os dados do usuário deveriam ser iguais"));
    }

    private UserAccount getUser() {
        return new UserAccount.Builder()
                .fullName("Netpos")
                .email("netpos@netpos.com")
                .senha("netpos")
                .build();
    }
}