package com.dce.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dce.model.Mail;
import com.dce.repository.MailRepository;
import com.dce.service.MailService;
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
	private String frommail;
	
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.host}")
	private String alihost;
	/**
	 * 发送文本邮件
	 *  @param to 收件人
	 *  @param subject 主题
	 *  @param content 内容
	 */
	@Override
	public void sendSimpleMail(Mail mail) {
		// 配置发送邮件的环境属性
        final Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");
        
//        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.host", alihost);
        props.put("mail.user", username);
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", password);
        try {
        	Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    return new PasswordAuthentication(username, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession) {};
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(frommail, username);
            message.setFrom(from);
            //可选。设置回信地址
//            Address[] a = new Address[1];
//            a[0] = new InternetAddress(props.getProperty("mail.fromMail.addr"));
//            message.setReplyTo(a);
            // 收件人邮件地址
            InternetAddress to = new InternetAddress(mail.getTo());
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            
//            String ccUser = "抄送邮箱";
//            // 设置多个抄送地址
//            if(null != ccUser && !ccUser.isEmpty()){
//                @SuppressWarnings("static-access")
//                InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
//                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
//            }
//            String bccUser = "密送邮箱";
//            // 设置多个密送地址
//            if(null != bccUser && !bccUser.isEmpty()){
//                @SuppressWarnings("static-access")
//                InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
//                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
//            }
            // 设置邮件标题
            message.setSubject(mail.getSubject());
            // 设置邮件的内容体
            message.setContent(mail.getContent(), "text/html;charset=UTF-8");
            
            
//            mailSender.send(message);
            Transport.send(message);
            System.out.println("发送成功！");
			mailRepository.save(mail);
		} catch (Exception e) {
			System.out.println("发送失败");
			e.printStackTrace();
		}
		
        
        
        
        
		// 封装邮件信息
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(from);
//		message.setTo(mail.getTo());
//		message.setSubject(mail.getSubject());
//		message.setText(mail.getContent());
		// 发送
//		try {
//			mailSender.send(message);
//			System.out.println("发送成功！");
//			mailRepository.save(mail);
//		} catch (Exception e) {
//			System.out.println("发送失败");
//		}
		
	}
	
	@Override
	public  void sendEmailSSL(Mail mail) {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(frommail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());

            Transport.send(message);
            mailRepository.save(mail);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Mail> getMailList() {
		return  mailRepository.findAll();
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
