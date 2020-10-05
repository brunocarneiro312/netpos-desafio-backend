package com.netpos.desafiobackend.dto.request;

import com.netpos.desafiobackend.enums.OperationEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author brunocarneiro
 */
@Getter
@Setter
public class ProductStockUpdate {

    private OperationEnum operation;
    private Integer quantity;

}
