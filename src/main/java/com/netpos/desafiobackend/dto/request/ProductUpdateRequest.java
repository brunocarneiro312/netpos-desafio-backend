package com.netpos.desafiobackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {

    private String name;
    private Float price;

}
