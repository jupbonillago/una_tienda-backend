package com.ingesoft2.mail;

public class EmailBody {
	private String email;
	private String content;
	private String subject;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "EmailBody [email=" + email + ", content=" + content + ", subject=" + subject + "]";
	}

	public EmailBody() {

	}

	public EmailBody(String email, String content) {
		this.email = email;
		this.content = content;
		this.subject = "Password reset request";
	}

	public EmailBody(String email, String content, String subject) {
		this.email = email;
		this.content = content;
		this.subject = subject;
	}

}
