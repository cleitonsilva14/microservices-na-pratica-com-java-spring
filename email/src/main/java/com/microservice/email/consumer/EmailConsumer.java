package com.microservice.email.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.microservice.email.dto.EmailRecordDto;
import com.microservice.email.model.EmailModel;
import com.microservice.email.service.EmailService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;


	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
		var emailModel = new EmailModel();
		
		BeanUtils.copyProperties(emailRecordDto, emailModel);
		
		// Send Email
		emailService.sendEmail(emailModel);
		
	}
	
}
