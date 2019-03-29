package com.rms.marketing.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ad_play_summary")
public class AdPlaySummary {

    @Id
    @Column(name = "summary_id")
    private String summaryId;

    @Column(name = "summary_date")
    private Date summaryDate;

    @Column(name = "channel_type")
    private String channelType;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "request_count")
    private Integer requestCount;

    @Column(name = "no_play_count")
    private Integer noPlayCount;

    @Column(name = "play_count")
    private Integer playCount;

    @Column(name = "valid_count")
    private Integer validCount;

    @Column(name = "invalid_count")
    private Integer invalidCount;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public Date getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(Date summaryDate) {
        this.summaryDate = summaryDate;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getNoPlayCount() {
        return noPlayCount;
    }

    public void setNoPlayCount(Integer noPlayCount) {
        this.noPlayCount = noPlayCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getValidCount() {
        return validCount;
    }

    public void setValidCount(Integer validCount) {
        this.validCount = validCount;
    }

    public Integer getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(Integer invalidCount) {
        this.invalidCount = invalidCount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
