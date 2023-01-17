package com.xrest.order_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems extends BaseModel<Long> {

    private String skuCode;
    private BigDecimal price;
    private Integer qty;

}
