package com.rms.marketing.service;

import com.rms.marketing.Application;
import com.rms.marketing.job.SendReportJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SendReportJobTest {

    @Test
    public void dd(){
        SendReportJob sendReportJob=new SendReportJob();

        sendReportJob.getScanData(new Date());
    }
}
