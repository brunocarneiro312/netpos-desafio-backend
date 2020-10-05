package com.netpos.desafiobackend.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
public class CreateUserAccountRequest {

    private String email;
    private String fullName;
    private String password;
}
