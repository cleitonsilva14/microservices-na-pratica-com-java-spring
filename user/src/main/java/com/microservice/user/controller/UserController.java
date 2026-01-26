package com.microservice.user.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.dto.UserRecordDto;
import com.microservice.user.model.UserModel;
import com.microservice.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
		
		var userModel = new UserModel();
		
		BeanUtils.copyProperties(userRecordDto, userModel);
		
		return ResponseEntity
				.status(CREATED).body(userService.save(userModel));
	
	}
	
}
