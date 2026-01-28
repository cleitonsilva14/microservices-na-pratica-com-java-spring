package com.microservice.email.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.microservice.email.enums.StatusEmail;
import com.microservice.email.model.EmailModel;
import com.microservice.email.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final EmailRepository emailRepository;
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;

	public EmailModel sendEmail(EmailModel emailModel) {
		
		try {
			emailModel.setLocalDateTime(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			
			simpleMailMessage.setTo(emailModel.getEmailTo());
			simpleMailMessage.setSubject(emailModel.getSubject());
			simpleMailMessage.setText(emailModel.getText());
			
			mailSender.send(simpleMailMessage);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException exception) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return emailRepository.save(emailModel);
		}
		
	}
	
	public EmailModel sendEmailWithLogo(EmailModel emailModel) {
		
		emailModel.setLocalDateTime(LocalDateTime.now());
		emailModel.setEmailFrom(emailFrom);
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			
			mailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			
		}catch (MailException exception) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}
		
		
		return emailRepository.save(emailModel);
		
	}
	
	
}
