package com.rms.marketing.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.rms.marketing.mapper.MongodbDao;
import com.rms.marketing.model.response.SendEmailParam;
import com.rms.marketing.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class SendReportJob implements SimpleJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private MongodbDao mongodbDao;

    @Value("${spring.profiles.active}")
    private String ev;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("当前运行环境" + ev);
        String receiveEmail = "yuanxiwang@doctorwork.com";
        if (ev == "prod") {
            receiveEmail = "yuanxiwang@doctorwork.com,cuishengyu@doctorwork.com";
        }
        System.out.println("收件人:" + receiveEmail);
        System.out.println("定时发送邮件开始");

        SendEmailParam sendEmailParam = new SendEmailParam();
        sendEmailParam.setSenderEmail("yuanxiwang@doctorwork.com");
        sendEmailParam.setSenderEmailPassword("Yxw2007");
        sendEmailParam.setReceiveEmail(receiveEmail);
        sendEmailParam.setTheme("健康终端扫码量，心选跳转统计");

        Date date = new Date();
        String content = getScanData(date);
        content = content + getUrineToXinXuanData(date);
        logger.debug("定时发送邮件日期:" + formatDate(date, "yyyy-MM-dd") + ",content={}", content);
        sendEmailParam.setContent(content);
        sendEmailService.send(sendEmailParam);
        System.out.println("定时发送邮件结束");

    }

    /**
     * 获取前一天的扫码量，以及前一天所在月截止到前一天扫码量
     *
     * @param date
     * @return
     */
    public String getScanData(Date date) {
        String content = "健康终端扫码量</br>";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); //加上时间
        String startDateStrShort = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-01");
        String endDateStrShort = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-dd");
        String startDateStr = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-01 00:00:00");
        String endDateStr = formatDate(date, "yyyy-MM-dd 00:00:00");
        Date startDate = null;
        Date endDate = null;
        logger.debug("startDateStr={},endDateStr={}", startDateStr, endDateStr);
        try {
            startDate = sDateFormat.parse(startDateStr);
            endDate = sDateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(startDate);

        logger.debug("startDate={},endDate={}", startDate, endDate);
        Map<String, Object> mapSum = mongodbDao.queryMachineScanRecordInfo(startDate, endDate);
        Map<String, Object> mapCurr = mongodbDao.queryMachineScanRecordInfo(addDate(endDate, Calendar.HOUR, -24), endDate);

        Integer currCount = mapCurr.containsKey("sum") ? Integer.valueOf(mapCurr.get("sum").toString()) : 0;
        Integer sumCount = mapSum.containsKey("sum") ? Integer.valueOf(mapSum.get("sum").toString()) : 0;
        content = content + endDateStrShort + " 当日扫码量：" + currCount.toString() + "</br>";
        content = content + startDateStrShort + "~" + endDateStrShort + " 总扫码量：" + sumCount.toString() + "</br>";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate(date, Calendar.HOUR, -24));
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        content = content + startDateStrShort + "~" + endDateStrShort + " 日均扫码量：" + (double) Math.round(sumCount * 100 / (day)) / 100 + "</br>";
        return content;
    }

    /**
     * 获取前一天的健康终端到心选跳转量，以及前一天所在月截止到前一天跳转量
     *
     * @param date
     * @return
     */
    public String getUrineToXinXuanData(Date date) {
        String content = "健康终端跳转心选统计</br>";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); //加上时间
        String startDateStrShort = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-01");
        String endDateStrShort = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-dd");
        String startDateStr = formatDate(addDate(date, Calendar.HOUR, -24), "yyyy-MM-01 00:00:00");
        String endDateStr = formatDate(date, "yyyy-MM-dd 00:00:00");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sDateFormat.parse(startDateStr);
            endDate = sDateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(startDate);

        Map<String, Object> mapNoLoginSum = mongodbDao.queryUrineToXinXuanRecordInfo(startDate, endDate, false);
        Map<String, Object> mapLoginSum = mongodbDao.queryUrineToXinXuanRecordInfo(startDate, endDate, true);
        Map<String, Object> mapNoLoginCurr = mongodbDao.queryUrineToXinXuanRecordInfo(addDate(endDate, Calendar.HOUR, -24), endDate, false);
        Map<String, Object> mapLoginCurr = mongodbDao.queryUrineToXinXuanRecordInfo(addDate(endDate, Calendar.HOUR, -24), endDate, true);

        Integer currNoLoginCount = mapNoLoginCurr.containsKey("sum") ? Integer.valueOf(mapNoLoginCurr.get("sum").toString()) : 0;
        Integer currLoginCount = mapLoginCurr.containsKey("sum") ? Integer.valueOf(mapLoginCurr.get("sum").toString()) : 0;

        Integer sumNoLoginCount = mapNoLoginSum.containsKey("sum") ? Integer.valueOf(mapNoLoginSum.get("sum").toString()) : 0;
        Integer sumLoginCount = mapLoginSum.containsKey("sum") ? Integer.valueOf(mapLoginSum.get("sum").toString()) : 0;
        content = content + endDateStrShort + " 当日总跳转：" + (currNoLoginCount + currLoginCount) + " 次,  其中未登录跳转：" + currNoLoginCount.toString() + " 次, 已登录跳转" + currLoginCount.toString() + " 次</br>";

        content = content + startDateStrShort + "~" + endDateStrShort + " 总跳转：" + (sumNoLoginCount + sumLoginCount) + " 次,  其中未登录跳转：" + sumNoLoginCount.toString() + " 次, 已登录跳转" + sumLoginCount.toString() + " 次</br>";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate(date, Calendar.HOUR, -24));
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        content = content + startDateStrShort + "~" + endDateStrShort + " 日均跳转量：" + (double) Math.round((sumNoLoginCount + sumLoginCount) * 100 / (day)) / 100 + "</br>";
        return content;
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern); //加上时间
        //必须捕获异常
        String dateStr = sDateFormat.format(date);
        return dateStr;
    }

    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -24);
        date = calendar.getTime();
        String startDateStr = formatDate(date, "yyyy-MM-01 00:00:00");
        String endDateStr = formatDate(date, "yyyy-MM-dd 00:00:00");
        System.out.println(startDateStr);
        System.out.println(endDateStr);
    }
}
