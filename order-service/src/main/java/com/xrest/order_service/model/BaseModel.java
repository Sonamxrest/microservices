package com.xrest.order_service.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public class BaseModel <T extends Serializable> extends AbstractPersistable<T> {
    @Override
    protected void setId(T id) {
        super.setId(id);
    }
}
