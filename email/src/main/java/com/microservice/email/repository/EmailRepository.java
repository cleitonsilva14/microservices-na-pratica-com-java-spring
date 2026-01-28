package com.microservice.email.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.email.model.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
