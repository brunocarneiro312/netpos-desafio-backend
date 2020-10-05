package com.netpos.desafiobackend.controller;

import com.netpos.desafiobackend.dto.request.CreateUserAccountRequest;
import com.netpos.desafiobackend.entity.UserAccount;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author brunocarneiro
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * --------------------
     * Listagem de usuários
     * --------------------
     * @param filter
     * @return
     */
    @GetMapping
    public ResponseEntity<List<UserAccount>> list(@RequestParam(value = "q", required = false, defaultValue = "") String filter) {
        try {
            return new ResponseEntity<>(this.userAccountService.filterByName(filter), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    /**
     * --------------------
     * Cria um novo usuário
     * --------------------
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<UserAccount> post(@RequestBody CreateUserAccountRequest request) {
        try {
            UserAccount userAccount = new UserAccount.Builder()
                    .email(request.getEmail())
                    .fullName(request.getFullName())
                    .senha(request.getPassword())
                    .build();

            return new ResponseEntity<>(this.userAccountService.save(userAccount), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * ------------------------------
     * Retorna os dados de um usuário
     * ------------------------------
     * @param userId
     * @return
     */
    @GetMapping("{user_id}")
    public ResponseEntity<UserAccount> get(@PathVariable("user_id") Integer userId) {
        try {
            return new ResponseEntity<>(this.userAccountService.findById(userId), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
