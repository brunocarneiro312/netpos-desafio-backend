package com.netpos.desafiobackend.dto.request;

import com.netpos.desafiobackend.entity.Stock;
import lombok.Getter;
import lombok.Setter;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
public class ProductCreateRequest {

    private String code;
    private String name;
    private Float price;
    private Stock stock;

}
