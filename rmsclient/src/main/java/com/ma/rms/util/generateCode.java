package com.ma.rms.util;


import java.security.Security;   
import java.util.Date;     
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;   
import javax.mail.Message;   
import javax.mail.Multipart;   
import javax.mail.PasswordAuthentication;   
import javax.mail.Session;   
import javax.mail.Transport;   
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeBodyPart;   
import javax.mail.internet.MimeMessage;   
import javax.mail.internet.MimeMultipart;   

/**  
 * 使用Gmail发送邮件  
 *   
 * @author Rain Chen  
 */  
public class generateCode {   

	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";   

	private static final String USERNAME = "1250903303@qq.com";//设定邮箱的用户名   

	private static final String PASSWORD = "jqscjxnnjdmtihcd";//设定邮箱的密码   
//"1250903303@qq.com"
	private static final String TO_EMAIL =null ;//设定收件人的信箱   

	private static final String SUBJECT = "验证码!";//设定邮件标题   

	private static final String FROM = "1250903303@qq.com";//设定发件件的人   

	private static final String SMTP = "smtp.qq.com";   

	@SuppressWarnings("restriction")
	public static void sender(String subject, String content,String to_email) throws Exception {   

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());   
		// Get a Properties object   
		Properties props = System.getProperties();   
		props.setProperty("mail.smtp.host", SMTP);   
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
		props.setProperty("mail.smtp.port", "465");   
		props.setProperty("mail.smtp.socketFactory.port", "465");   
		props.put("mail.smtp.auth", "true");   

		Session session = Session.getDefaultInstance(props,   
				new Authenticator() {   
			protected PasswordAuthentication getPasswordAuthentication() {   
				return new PasswordAuthentication(USERNAME, PASSWORD);   
			}   
		});   

		Message msg = new MimeMessage(session);   
		InternetAddress[] address = null;   
		// 设定发邮件的人   
		msg.setFrom(new InternetAddress(FROM));   

		// 设定收信人的信箱   
		address = InternetAddress.parse(to_email, false);   
		msg.setRecipients(Message.RecipientType.TO, address);   

		// 设定信中的主题   
		msg.setSubject(subject);   

		// 设定送信的时间   
		msg.setSentDate(new Date());   

		Multipart mp = new MimeMultipart();   
		MimeBodyPart mbp = new MimeBodyPart();   

		// 设定邮件内容的类型为 text/plain 或 text/html   
		mbp.setContent(content, "text/html;charset=GB2312");   
		mp.addBodyPart(mbp);   
		msg.setContent(mp);   

		Transport transport = session.getTransport("smtp");   
		transport.connect(SMTP, USERNAME, PASSWORD);   
		transport.sendMessage(msg, msg.getAllRecipients());   
		transport.close();   
	}   
	//生成8位随机验证码
	public static String[] chars=new String[] {"A","B","C","D","E","F","G","H","I","J","K"
			,"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5",		
			"6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
			"r","s","t","u","v","w","x","y","z"};

	public static  String generateShortUuid() {
		StringBuffer shortBuffer=new StringBuffer();
		String uuid=UUID.randomUUID().toString().replace("-", "");
		for(int i=0;i<8;i++) {
			String str=uuid.substring(i*4, i*4+4);
			int x=Integer.parseInt(str,16);
			shortBuffer.append(chars[x%0x3E]);
		}
		return shortBuffer.toString();
	}

//	public static void main(String[] args) throws Exception {   
//
//		sender(SUBJECT, generateShortUuid());   
//		System.out.println("Message sent.");   
//	}   
	public String sendMail(String to_email) {
		String verificationCode= generateShortUuid();
		try {
			sender(SUBJECT, verificationCode,to_email);
			return verificationCode;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}   
		
	}
}   