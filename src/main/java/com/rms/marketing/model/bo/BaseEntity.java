package com.rms.marketing.model.bo;

import javax.persistence.*;

/**
 * 基础信息
 *
 * @author wk
 * @since 2016-01-31 21:42
 */
public class BaseEntity{
    @Id
    @Column(name = "KeyID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select REPLACE(uuid(),'-','')")
//    @GeneratedValue(generator = "UUIDUtil")
    private String keyID;
    /**
     * @是否删除
     */
    @Column(name = "IsDelete")
    private Integer isDelete=0;
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}
