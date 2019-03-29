package com.rms.marketing.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`ad_machine_info`")
public class AdMachineInfo {
    /**
     * 机器id
     */
    @Id
    @Column(name = "`machine_id`")
    private String machineId;

    /**
     * 经度
     */
    @Column(name = "`longitude`")
    private String longitude;

    /**
     * 纬度
     */
    @Column(name = "`latitude`")
    private String latitude;

    /**
     * 插入时间
     */
    @Column(name = "`add_time`")
    private Date addTime;

    /**
     * 更新时间
     */
    @Column(name = "`modify_time`")
    private Date modifyTime;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    private Boolean isDelete;

    /**
     * 易售平台图片广告位id
     */
    @Column(name = "`yishou_img_slot_id`")
    private String yishouImgSlotId;

    /**
     * 易售平台视频广告位id
     */
    @Column(name = "`yishou_video_slot_id`")
    private String yishouVideoSlotId;

    /**
     * 获取机器id
     *
     * @return machine_id - 机器id
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置机器id
     *
     * @param machineId 机器id
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取插入时间
     *
     * @return add_time - 插入时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置插入时间
     *
     * @param addTime 插入时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取更新时间
     *
     * @return modify_time - 更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置更新时间
     *
     * @param modifyTime 更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取易售平台图片广告位id
     *
     * @return yishou_img_slot_id - 易售平台图片广告位id
     */
    public String getYishouImgSlotId() {
        return yishouImgSlotId;
    }

    /**
     * 设置易售平台图片广告位id
     *
     * @param yishouImgSlotId 易售平台图片广告位id
     */
    public void setYishouImgSlotId(String yishouImgSlotId) {
        this.yishouImgSlotId = yishouImgSlotId;
    }

    /**
     * 获取易售平台视频广告位id
     *
     * @return yishou_video_slot_id - 易售平台视频广告位id
     */
    public String getYishouVideoSlotId() {
        return yishouVideoSlotId;
    }

    /**
     * 设置易售平台视频广告位id
     *
     * @param yishouVideoSlotId 易售平台视频广告位id
     */
    public void setYishouVideoSlotId(String yishouVideoSlotId) {
        this.yishouVideoSlotId = yishouVideoSlotId;
    }
}