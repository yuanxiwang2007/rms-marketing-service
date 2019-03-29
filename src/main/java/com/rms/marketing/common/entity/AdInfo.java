package com.rms.marketing.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Document( indexName = "#{ElasticSearchIndexConfig.adInfoIndex}", type = "#{ElasticSearchIndexConfig.adInfoTypeName}", refreshInterval = "-1", createIndex = true)
public class AdInfo {
    /**
     * 广告id
     */
    @Id
    protected String adId;

    /**
     * 对应机器id
     */
    @Field(index = false)
    protected String machineId;

    /**
     * 对应的广告渠道
     */
    @Field(index = false)
    protected String channelType;

    /**
     * 广告媒体的url地址
     */
    @Field(index = false)
    protected String mediaUrl;

    /**
     * 广告媒体的md5
     */
    @Field(index = false)
    protected String mediaMd5;

    /**
     * 广告媒体类型 图片/视频 MediaTypeEnum
     */
    @Field(index = false)
    protected String mediaType;

    /**
     * 广告持续时间
     */
    protected Integer mediaDuration;

    /**
     * 请求时间
     */
    protected Date requestTime;

    /**
     * 外部第三方所有的参数信息
     */
    @Field(index = false)
    protected String outParams;

    /**
     * 获取广告id
     *
     * @return ad_id - 广告id
     */
    public String getAdId() {
        return adId;
    }

    /**
     * 设置广告id
     *
     * @param adId 广告id
     */
    public void setAdId(String adId) {
        this.adId = adId;
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
     * 获取对应的广告渠道
     *
     * @return channel_type - 对应的广告渠道
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * 设置对应的广告渠道
     *
     * @param channelType 对应的广告渠道
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * 获取广告媒体的url地址
     *
     * @return media_url - 广告媒体的url地址
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * 设置广告媒体的url地址
     *
     * @param mediaUrl 广告媒体的url地址
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    /**
     * 获取广告媒体的md5
     *
     * @return media_md5 - 广告媒体的md5
     */
    public String getMediaMd5() {
        return mediaMd5;
    }

    /**
     * 设置广告媒体的md5
     *
     * @param mediaMd5 广告媒体的md5
     */
    public void setMediaMd5(String mediaMd5) {
        this.mediaMd5 = mediaMd5;
    }

    /**
     * 获取广告媒体类型 图片/视频 MediaTypeEnum
     *
     * @return media_type - 广告媒体类型 图片/视频 MediaTypeEnum
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * 设置广告媒体类型 图片/视频 MediaTypeEnum
     *
     * @param mediaType 广告媒体类型 图片/视频 MediaTypeEnum
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 获取广告持续时间
     *
     * @return media_duration - 广告持续时间
     */
    public Integer getMediaDuration() {
        return mediaDuration;
    }

    /**
     * 设置广告持续时间
     *
     * @param mediaDuration 广告持续时间
     */
    public void setMediaDuration(Integer mediaDuration) {
        this.mediaDuration = mediaDuration;
    }

    /**
     * 获取请求时间
     *
     * @return request_time - 请求时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 设置请求时间
     *
     * @param requestTime 请求时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 获取外部第三方所有的参数信息
     *
     * @return out_params - 外部第三方所有的参数信息
     */
    public String getOutParams() {
        return outParams;
    }

    /**
     * 设置外部第三方所有的参数信息
     *
     * @param outParams 外部第三方所有的参数信息
     */
    public void setOutParams(String outParams) {
        this.outParams = outParams;
    }
}