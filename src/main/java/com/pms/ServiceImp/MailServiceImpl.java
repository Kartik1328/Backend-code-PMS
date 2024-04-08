package com.pms.ServiceImp;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl {
	
	@Autowired
	ThymeLeafService thymeleafService;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String toEmail;
	
	public String sendMail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
		MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
		StandardCharsets.UTF_8.name());

		Map<String, Object> variables = new HashMap<>();
		variables.put("Name","Kartikey Srivastava");

		helper.setFrom(toEmail);
		helper.setText(thymeleafService.createContent("mail-sender-test.html", variables), true);
		helper.setTo("kumar_srivastav@cms.co.in");
		helper.setSubject("mail test with HTML template");

		mailSender.send(mimeMessage);
		return "success";
		}


}
