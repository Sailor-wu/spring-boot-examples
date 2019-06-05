package com.neo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 邮箱
 * @author Administrator
 */
@Entity
public class Mail {
	
	@Id
    @GeneratedValue
    private long id;
	
    @Column(nullable = false,name = "tosb")
	private String to;
    
    @Column(nullable = false )
	private String subject;
    
    @Column(nullable = false )
	private String content;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	/**获取收件人信息*/
	public String getTo() {
		return to;
	}
	
	
	public void setTo(String to) {
		this.to = to;
	}
	
	/**主题*/
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**内容*/
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
