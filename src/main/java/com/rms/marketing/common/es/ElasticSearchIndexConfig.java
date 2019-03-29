package com.rms.marketing.common.es;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("ElasticSearchIndexConfig")
public class ElasticSearchIndexConfig {

    private String adInfoIndex;
    private String adPlayRecordIndex;

    public static final String adInfoTypeName = "ad_info";
    public static final String adInfoIndexName = "ad_info"; // 就是adInfo的index的别名
    public static final String adPlayRecordTypeName = "ad_play_record";
    public static final String adPlayRecordIndexName = "ad_play_record"; // 就是adPlayRecord的index的别名

    private FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy_MM");

    public String getAdInfoIndex() {
        return adInfoIndexName + "_" + ISO_DATE_FORMAT.format(new Date());
    }

    public String nextAdInfoIndex(int month) {
        return adInfoIndexName + "_" + ISO_DATE_FORMAT.format(DateUtils.addMonths(new Date(), month));
    }

    public String getAdPlayRecordIndex() {
        return adPlayRecordIndexName + "_" + ISO_DATE_FORMAT.format(new Date());
    }

    public String nextAdPlayRecordIndex(int month) {
        return adPlayRecordIndexName + "_" + ISO_DATE_FORMAT.format(DateUtils.addMonths(new Date(), month));
    }

    public static String getAdInfoTypeName() {
        return adInfoTypeName;
    }

    public static String getAdPlayRecordTypeName() {
        return adPlayRecordTypeName;
    }
}
