package com.pms.ServiceImpl;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.pms.Model.MasterDatabase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class PmsMailServiceImpl {

    @Autowired
    ThymeLeafService thymeleafService;

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public String sendMail() throws MessagingException {
        return sendMail("Kartikey Srivastava", "kumar_srivastav@cms.co.in", "mail-sender-test.html", "mail test with HTML template");
    }

    public String sendKraSubmittedMail() throws MessagingException {
        return sendMail("Amulya", "amulya_s@cms.co.in", "mail-sender-test.html", "mail test with HTML template");
    }

    public String sendApprovesMail() throws MessagingException {
        return sendMail("Amulya", "amulya_s@cms.co.in", "mail-sender-test.html", "mail test with HTML template");
    }

    public String sendRevertBackMail(MasterDatabase masterDatabase) throws MessagingException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("Name", "Amulya");
        variables.put("Comments", masterDatabase.getMgrRevertComments());

        return sendMail("Amulya", "amulya_s@cms.co.in", "mail-sender-test.html", "mail test with HTML template", variables);
    }

    private String sendMail(String name, String toEmail, String template, String subject) throws MessagingException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("Name", name);
        return sendMail(name, toEmail, template, subject, variables);
    }

    private String sendMail(String name, String toEmail, String template, String subject, Map<String, Object> variables) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(thymeleafService.createContent(template, variables), true);

        mailSender.send(mimeMessage);
        return "success";
    }
}
