package com.indsys.iLabelBackEnd.productColor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "INDSYS1010ProductColor")
public class ProductColor {

    @Id
    private String productColor;

    private String createdUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateTime;

    private String LastModifiedUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastModifiedDateTime;

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedUserId() {
        return LastModifiedUserId;
    }

    public void setLastModifiedUserId(String lastModifiedUserId) {
        LastModifiedUserId = lastModifiedUserId;
    }

    public Date getLastModifiedDateTime() {
        return LastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        LastModifiedDateTime = lastModifiedDateTime;
    }
}
