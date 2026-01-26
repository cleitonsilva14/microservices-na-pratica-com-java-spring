package com.microservice.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.user.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
