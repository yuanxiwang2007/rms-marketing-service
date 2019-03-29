package com.rms.marketing.model.bo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wk
 * @用户信息表
 */
@Table(name = "UserInfo")
public class UserInfoBo extends BaseEntity {
    /**
     * @外部用户姓名
     */
    @Column(name = "OutUserID")
    private String outUserID;
    /**
     * @外部用户姓名
     */
    @Column(name = "OutUserName")
    private String outUserName;
    /**
     * @微信统一ID
     */
    @Column(name = "UnionID")
    private String unionID;
    /**
     * @用户ID
     */
    @Column(name = "UserID")
    private String userID;
    /**
     * @用户姓名
     */
    @Column(name = "UserName")
    private String userName;
    /**
     * @电话
     */
    @Column(name = "Phone")
    private String phone;
    /**
     * @性别
     */
    @Column(name = "SexType")
    private Integer sexType;
    /**
     * @头像
     */
    @Column(name = "HeadImgUrl")
    private String headImgUrl;
    /**
     * @性别名称
     */
    @Column(name = "SexTypeName")
    private String sexTypeName;
    /**
     * @购买次数
     */
    @Column(name = "BuyNum")
    private Integer buyNum;
    /**
     * @检测次数
     */
    @Column(name = "CheckNum")
    private Integer checkNum;
    /**
     * @上次登录时间
     */
    @Column(name = "LastLogin")
    private Date lastLogin;
    /**
     * @添加时间
     */
    @Column(name = "AddTime")
    private Date addTime;
    /**
     * @修改时间
     */
    @Column(name = "ModifyTime")
    private Date modifyTime;

    /**
     * 是否被冻结 0 正常 1 封禁
     */
    @Column(name = "IsFreeze")
    private Integer isFreeze;

    /**
     * IP地址
     */
    @Column(name = "IpAddress")
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return 外部用户姓名
     */
    public String getOutUserID() {
        return outUserID;
    }

    /**
     * @param outUserID 外部用户姓名
     */
    public void setOutUserID(String outUserID) {
        this.outUserID = outUserID;
    }

    /**
     * @return 外部用户姓名
     */
    public String getOutUserName() {
        return outUserName;
    }

    /**
     * @param outUserName 外部用户姓名
     */
    public void setOutUserName(String outUserName) {
        this.outUserName = outUserName;
    }

    /**
     * @return 微信统一ID
     */
    public String getUnionID() {
        return unionID;
    }

    /**
     * @param unionID 微信统一ID
     */
    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    /**
     * @return 用户ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID 用户ID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 性别
     */
    public Integer getSexType() {
        return sexType;
    }

    /**
     * @param sexType 性别
     */
    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }

    /**
     * @return 头像
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * @param headImgUrl 头像
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return 性别名称
     */
    public String getSexTypeName() {
        return sexTypeName;
    }

    /**
     * @param sexTypeName 性别名称
     */
    public void setSexTypeName(String sexTypeName) {
        this.sexTypeName = sexTypeName;
    }

    /**
     * @return 购买次数
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * @param buyNum 购买次数
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * @return 检测次数
     */
    public Integer getCheckNum() {
        return checkNum;
    }

    /**
     * @param checkNum 检测次数
     */
    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    /**
     * @return 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }
}
