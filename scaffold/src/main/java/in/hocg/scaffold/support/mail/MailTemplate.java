package in.hocg.scaffold.support.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by hocgin on 2018/10/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Deprecated
public class MailTemplate {
    private String from;
    private String personal = "hocg.in 官方邮件";
    
    private JavaMailSender mailSender;
    private HttpServletRequest request;
    private ServletContext servletContext;
    private HttpServletResponse response;
    
    public MailTemplate(JavaMailSender mailSender,
                        HttpServletRequest request,
                        ServletContext servletContext,
                        HttpServletResponse response) {
        this.mailSender = mailSender;
        this.servletContext = servletContext;
        this.request = request;
        this.response = response;
    }
    
    /**
     * 邮件发送
     *
     * @param to         收件人
     * @param subject    标题
     * @param text       内容
     * @param inline     图片
     * @param attachment 附件
     * @throws UnsupportedEncodingException
     */
    
    public void send(String[] to, String subject, String text,
                     Map<String, File> inline,
                     Map<String, File> attachment) throws javax.mail.MessagingException, UnsupportedEncodingException {
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(to);
        messageHelper.setFrom(from, personal);
        
        // - 给自己邮箱抄录一份
        messageHelper.setCc(from);
        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);
        
        // 图片
        if (!MapUtils.isEmpty(inline)) {
            inline.forEach((k, v) -> {
                try {
                    messageHelper.addInline(k, v);
                } catch (javax.mail.MessagingException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 附件
        if (!MapUtils.isEmpty(attachment)) {
            attachment.forEach((k, v) -> {
                try {
                    String filename = MimeUtility.encodeWord(k)
                            .replaceAll("\r", "")
                            .replaceAll("\n", "");
                    messageHelper.addAttachment(filename, v);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
        }
        mailSender.send(messageHelper.getMimeMessage());
    }
    
    
    public void send(String to, String subject, String text,
                     Map<String, File> inline,
                     Map<String, File> attachment) throws javax.mail.MessagingException, UnsupportedEncodingException {
        send(new String[]{to}, subject, text, inline, attachment);
    }
    
    
    public void send(String to, String subject, String text) throws UnsupportedEncodingException, javax.mail.MessagingException {
        send(to, subject, text, null, null);
    }
    
    
}
