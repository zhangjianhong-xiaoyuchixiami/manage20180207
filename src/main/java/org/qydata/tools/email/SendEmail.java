package org.qydata.tools.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import java.net.URL;

/**
 * Created by jonhn on 2017/2/15.
 */
public class SendEmail {


    /**
     * 发送邮件
     * @param to 收件人邮箱地址
     * @param title 标题
     * @param content 内容
     */
    public static void sendMail(String [] to, String title, String content,String url,String emailName) throws Exception {

        //创建一个Email附件
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setURL(new URL(url));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("这是一个对账单");
        attachment.setName(emailName);//自定义文件名，并且格式要一致，不然邮件收到的话，有可能读不出来


        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setTLS(true);
            //发送主机服务器
            email.setHostName("smtp.mxhichina.com");
            //登陆邮件服务器的用户名和密码
            email.setAuthentication("accounting@qianyandata.com", "1qaz@WSX");
            //发送人
            email.setFrom("accounting@qianyandata.com");
            //接收人
            email.addTo(to);
            //标题
            email.setSubject(title);
            //编码
            email.setCharset("utf-8");
            //内容
            email.setMsg(content);
            //附件
            email.attach(attachment);
            //发送
            email.send();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}


