package com.neo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.neo.model.Mail;
import com.neo.repository.MailRepository;
import com.neo.service.MailService;
@Service
public class MailServiceImpl implements MailService {

	// 注入操作数据库对象
	@Autowired
	private MailRepository mailRepository;
	
	// 注入邮箱发送对象
	@Autowired
	private JavaMailSender mailSender;
	
	// 发件人系统固定 配置文件注入
	@Value("${mail.fromMail.addr}")
	private String from;
	/**
	 * 发送文本邮件
	 *  @param to 收件人
	 *  @param subject 主题
	 *  @param content 内容
	 */
	@Override
	public void sendSimpleMail(Mail mail) {
		// 封装邮件信息
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		// 发送
		try {
			mailSender.send(message);
			System.out.println("发送成功！");
			mailRepository.save(mail);
		} catch (Exception e) {
			System.out.println("发送失败");
		}
		
	}

//	@Override
//	public void sendHtmlMail(String to, String subject, String content) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
//		// TODO Auto-generated method stub
//
//	}

}
