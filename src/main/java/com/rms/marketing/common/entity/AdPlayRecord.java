package com.rms.marketing.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;

@Document( indexName = "#{ElasticSearchIndexConfig.adPlayRecordIndex}", type = "#{ElasticSearchIndexConfig.adPlayRecordTypeName}", refreshInterval = "-1", createIndex = true)
public class AdPlayRecord implements Serializable {

    @Id
    @Field(index = false)
    private String recordId;

    /**
     * 对应的广告id
     */
    @Field(index = false)
    private String adId;

    /**
     * 对应机器id
     */
    @Field(index = false)
    private String machineId;

    /**
     * 对应的广告渠道
     */
    @Field(index = false)
    private String channelType;

    /**
     * 是否是无效播放
     */
    @Field(index = false)
    private String invalidPlay;

    /**
     * 无效的原因
     */
    @Field(index = false)
    private String invalidMsg;

    /**
     * 开始播放时间
     */
    private Date startPlayTime;

    /**
     * 结束播放时间
     */
    private Date endPlayTime;

    /**
     * 播放持续多少毫秒
     */
    private Integer duration;

    /**
     * @return record_id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取对应的广告id
     *
     * @return ad_id - 对应的广告id
     */
    public String getAdId() {
        return adId;
    }

    /**
     * 设置对应的广告id
     *
     * @param adId 对应的广告id
     */
    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getInvalidMsg() {
        return invalidMsg;
    }

    public void setInvalidMsg(String invalidMsg) {
        this.invalidMsg = invalidMsg;
    }

    /**
     * 获取对应机器id
     *
     * @return machine_id - 对应机器id
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置对应机器id
     *
     * @param machineId 对应机器id
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取是否是无效播放
     *
     * @return invalid_play - 是否是无效播放
     */
    public String getInvalidPlay() {
        return invalidPlay;
    }

    /**
     * 设置是否是无效播放
     *
     * @param invalidPlay 是否是无效播放
     */
    public void setInvalidPlay(String invalidPlay) {
        this.invalidPlay = invalidPlay;
    }

    /**
     * 获取开始播放时间
     *
     * @return start_play_time - 开始播放时间
     */
    public Date getStartPlayTime() {
        return startPlayTime;
    }

    /**
     * 设置开始播放时间
     *
     * @param startPlayTime 开始播放时间
     */
    public void setStartPlayTime(Date startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    /**
     * 获取结束播放时间
     *
     * @return end_play_time - 结束播放时间
     */
    public Date getEndPlayTime() {
        return endPlayTime;
    }

    /**
     * 设置结束播放时间
     *
     * @param endPlayTime 结束播放时间
     */
    public void setEndPlayTime(Date endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * 获取播放持续多少秒
     *
     * @return duration - 播放持续多少秒
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置播放持续多少秒
     *
     * @param duration 播放持续多少秒
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}