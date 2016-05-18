package com.yash.yits.form;

public class MailForm {

	
	
	 private String mailFrom;

	 private String mailTo;

	 private String mailCc;

	 private String mailBcc;

	 private String mailSubject;

	 private String mailContent;

	 private String templateName;

	 private String contentType;

	
	 public MailForm(){
		 contentType = "text/html";
	}

	 public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	 public String toString() {
	  StringBuilder lBuilder = new StringBuilder();
	  lBuilder.append("Mail From:- ").append(getMailFrom());
	  lBuilder.append("Mail To:- ").append(getMailTo());
	  lBuilder.append("Mail Cc:- ").append(getMailCc());
	  lBuilder.append("Mail Bcc:- ").append(getMailBcc());
	  lBuilder.append("Mail Subject:- ").append(getMailSubject());
	  lBuilder.append("Mail Content:- ").append(getMailContent());
	  return lBuilder.toString();
	 }
}
