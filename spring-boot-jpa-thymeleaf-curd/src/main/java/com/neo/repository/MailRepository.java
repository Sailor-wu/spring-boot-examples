package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.model.Mail;

public interface MailRepository  extends JpaRepository<Mail, Long> {

	
	
}
