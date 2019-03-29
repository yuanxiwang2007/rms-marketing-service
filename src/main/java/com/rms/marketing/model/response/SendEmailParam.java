package com.rms.marketing.model.response;

/**
 * @Description：
 * @Author:yangxiao
 * @Date: Create in 2018/11/6 16:23
 * @Modified By:
 */
public class SendEmailParam {
    //发件人别名
    private String personal;
    //发送者邮箱
    private String senderEmail;
    //发送者密码
    private String senderEmailPassword;
    //收件人邮件
    private String receiveEmail;
    //主题
    private String theme;
    //内容
    private String content;
    //附件路径
    private String annexPath;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderEmailPassword() {
        return senderEmailPassword;
    }

    public void setSenderEmailPassword(String senderEmailPassword) {
        this.senderEmailPassword = senderEmailPassword;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnnexPath() {
        return annexPath;
    }

    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
