package bussiness.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Catalog implements Serializable {
    private long catalogId;
    private String catalogName;
    private String desc;
    private boolean status;

    public Catalog() {
    }
    public Catalog(long catalogId, String catalogName, String desc, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.desc = desc;
        this.status = status;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
