package com.dce.service;

import java.util.List;

import com.dce.model.Mail;

/**
 * 
 * 
 * @author Administrator
 */
public interface MailService {

	public void sendSimpleMail(Mail mail);

	public  void sendEmailSSL(Mail mail);
//    public void sendHtmlMail(String to, String subject, String content);
//
//    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
//
//    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

	public List<Mail> getMailList();
}
