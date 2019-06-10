package com.dce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dce.model.Mail;

public interface MailRepository  extends JpaRepository<Mail, Long> {

	
	
}
