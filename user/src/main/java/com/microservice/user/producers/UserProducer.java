package com.microservice.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservice.user.dto.EmailDto;
import com.microservice.user.model.UserModel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProducer {


	private final RabbitTemplate rabbitTemplate;
	
	// routingKey tem que ser o mesmo nome da fila criada
	@Value(value = "${broker.queue.email.name}")
	private String routingKey; 
	
	public void publishMessageEmail(UserModel userModel) {
		var emailDto = new EmailDto();
		
		emailDto.setUserId(userModel.getUserId());
		emailDto.setEmailTo(userModel.getEmail());
		emailDto.setSubject("Cadastro realizado com sucesso!");
		emailDto.setText(userModel.getName() + ", Seja Bem Vindo(a)! \n Agradecemos o seu cadastro na plataforma!");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDto);
		
	}
	
	
	
	
}
