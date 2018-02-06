package org.qydata.tools.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.qydata.tools.date.CalendarUtil;

import java.net.URL;

/**
 * Created by jonhn on 2017/2/15.
 */
public class SendEmail {


    /**
     * 发送邮件
     * @param to 收件人邮箱地址
     * @param title 标题
     */
    public static void sendMail(String [] to,String [] copyTo, String title, String url,String name) throws Exception {

        try {
            //创建一个Email附件
            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setURL(new URL(url));
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("这是一个对账单");
            attachment.setName(name+".xls");//自定义文件名，并且格式要一致，不然邮件收到的话，有可能读不出来

            HtmlEmail email = new HtmlEmail();
            email.setSSL(true);
            //发送主机服务器
            email.setHostName("smtp.mxhichina.com");
            email.setSslSmtpPort("465");
            //登陆邮件服务器的用户名和密码
            email.setAuthentication("accounting@qianyandata.com", "jf2ieaDI0)!jn");
            //发送人
            email.setFrom("accounting@qianyandata.com");
            //接收人
            email.addTo(to);

            if (copyTo != null){
                //添加抄送人地址
                email.addCc(copyTo);
            }
            //标题
            email.setSubject(title);
            //编码
            email.setCharset("utf-8");
            //内容
            email.setHtmlMsg("<html><div>您好！</div><div>&nbsp;&nbsp;&nbsp;&nbsp;附件是贵司"+ CalendarUtil.getCurrentDateLastMonthYear()+"年"+CalendarUtil.getCurrentDateLastMonthMonth()+"月"+"数据调用情况，请您核对。如有问题，请返回贵司统计结果，若对我司统计结果无异议，请邮件回复确认，谢谢！</div></html>");
            //附件
            email.attach(attachment);
            //发送
            email.send();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}


