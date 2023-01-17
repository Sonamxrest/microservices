package com.xrest.productservice.Model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.sql.Date;

public class BaseModel<T extends Serializable> extends AbstractPersistable<T> {

    @CreatedDate
    private Date createdAt;

    @Version
    private  int version;

    @LastModifiedDate
    private Date lastModifiedDate;

    @Override
    protected void setId(T id) {
        super.setId(id);
    }
}
