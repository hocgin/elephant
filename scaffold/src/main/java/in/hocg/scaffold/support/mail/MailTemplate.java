package in.hocg.scaffold.support.mail;

import in.hocg.scaffold.support.spel.SpelParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2018/10/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public final class MailTemplate {
    private String username;
    private String personal;
    
    private JavaMailSender mailSender;
    
    public MailTemplate(String username,
                        String personal,
                        JavaMailSender mailSender) {
        this.username = username;
        this.personal = personal;
        this.mailSender = mailSender;
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
        messageHelper.setFrom(username, personal);
        
        // - 给自己邮箱抄录一份
        messageHelper.setCc(username);
        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);
        
        // 图片
        if (Objects.nonNull(inline)) {
            inline.forEach((k, v) -> {
                try {
                    messageHelper.addInline(k, v);
                } catch (javax.mail.MessagingException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // 附件
        if (Objects.nonNull(attachment)) {
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
    
    public void sendUseSpel(String to, String subject, String text, EvaluationContext context,
                            Map<String, File> inline,
                            Map<String, File> attachment) throws UnsupportedEncodingException, MessagingException {
        String content = SpelParser.parser(text, context);
        send(to, subject, content, inline, attachment);
    }
    
    
    public void sendUseSpel(String to, String subject, String text, EvaluationContext context) throws UnsupportedEncodingException, MessagingException {
        sendUseSpel(to, subject, text, context, null, null);
    }
    
}
