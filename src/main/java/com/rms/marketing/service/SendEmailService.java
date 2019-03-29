package com.rms.marketing.service;

import com.rms.marketing.model.response.SendEmailParam;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * @Description： 企业邮箱发送邮件，发送者必须是企业邮箱，接受者可为任何可用的邮箱
 * @Author:yangxiao
 * @Date: Create in 2018/11/6 16:20
 * @Modified By:
 */
@Service
public class SendEmailService {
    //服务器地址（邮件服务器）
    private static String host = "smtp.exmail.qq.com";
    //端口
    private static String port = "465";
    //协议
    private static String protocol = "smtp";

    static class MyAuthenricator extends Authenticator {
        String u = null;
        String p = null;

        public MyAuthenricator(String u, String p) {
            this.u = u;
            this.p = p;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(u, p);
        }
    }

    public void send(SendEmailParam sendEmailParam) {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", protocol);
        //服务器
        prop.setProperty("mail.smtp.host", host);
        //端口
        prop.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(sendEmailParam.getSenderEmail(),
                sendEmailParam.getSenderEmailPassword()));
        session.setDebug(false);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //发件人
            mimeMessage.setFrom(new InternetAddress(sendEmailParam.getSenderEmail(), sendEmailParam.getPersonal()));        //可以设置发件人的别名
            //mimeMessage.setFrom(new InternetAddress(account));    //如果不需要就省略
            //收件人
            mimeMessage.addRecipients(Message.RecipientType.TO, sendEmailParam.getReceiveEmail());
            //主题
            mimeMessage.setSubject(sendEmailParam.getTheme());
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类，可以包含多个MimeBodyPart对象
            Multipart mp = new MimeMultipart();

            //MimeBodyPart可以包装文本，图片，附件
            MimeBodyPart body = new MimeBodyPart();
            //HTML正文
            body.setContent(sendEmailParam.getContent(), "text/html; charset=UTF-8");
            mp.addBodyPart(body);

            //添加图片&附件
            if (sendEmailParam.getAnnexPath() != null) {
                body = new MimeBodyPart();
                body.attachFile(sendEmailParam.getAnnexPath());
                mp.addBodyPart(body);
            }
            //设置邮件内容
            mimeMessage.setContent(mp);
            //仅仅发送文本
            //mimeMessage.setText(content);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
