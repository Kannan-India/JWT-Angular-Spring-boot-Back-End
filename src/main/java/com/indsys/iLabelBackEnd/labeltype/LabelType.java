package com.indsys.iLabelBackEnd.labeltype;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Document(collection = "INDSYS1012LabelType")

public class LabelType {
    @Id
    private String labelType;

    private String createdUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateTime;

    private String LastModifiedUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date LastModifiedDateTime;
    public java.lang.String getLabelType() {
        return labelType;
    }

    public void setLabelType(java.lang.String labelType) {
        this.labelType = labelType;
    }

    public java.lang.String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(java.lang.String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public java.lang.String getLastModifiedUserId() {
        return LastModifiedUserId;
    }

    public void setLastModifiedUserId(java.lang.String lastModifiedUserId) {
        LastModifiedUserId = lastModifiedUserId;
    }

    public Date getLastModifiedDateTime() {
        return LastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        LastModifiedDateTime = lastModifiedDateTime;
    }


}
