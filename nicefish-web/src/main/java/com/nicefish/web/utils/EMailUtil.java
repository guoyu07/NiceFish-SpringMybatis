package com.nicefish.web.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮件发送工具类
 * @author Lord
 */
public class EMailUtil {

	/**
	 * 发送邮件的方法
	 * @param to	：给谁发邮件
	 * @param code	：邮件的激活码
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public static void sendMail(String to, String code) throws AddressException, MessagingException{
		//1、创建连接对象，连接到邮箱服务器
		Properties props = new Properties();
		// 设置传输协议
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置发信邮箱的smtp地址
		props.setProperty("mail.smtp.host", "smtp.163.com");
		// 端口号
		props.setProperty("mail.smtp.port", "465");
		// 验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置是否使用ssl安全连接  ---一般都使用
		props.setProperty("mail.smtp.ssl.enable", "true");
		//便于调试
		props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("17691008810@163.com","wsc251314");
			}
		});
		//2、创建邮件对象
		Message message = new MimeMessage(session);
		//2.1设置发件人
		message.setFrom(new InternetAddress("17691008810@163.com"));
		//2.2设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//2.3设置邮件主题
		message.setSubject("来自NiceFish的邮件");
		//2.4设置邮件正文
		message.setContent("<h1>来自NiceFish网站的激活邮件,请点击以下链接激活:</h1><h3><a href='http://192.168.1.91:8080/NiceFish/active?code="+code+"'>http://192.168.1.91:8080/NiceFish/active?code="+code+"</h3>","text/html;charset=UTF-8");
		//2.5设置发信时间
		message.setSentDate(new Date());
		//3、发送一份激活信息
		Transport.send(message);
	}

}
