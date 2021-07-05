package com.vendetech.common.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年5月10日 上午10:36:09
 */
public class SendEmailUtil {
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailFrom;
    public static String timeout;
    public static String personal;
    public static Properties properties;
    static {
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        properties = new Properties();
        try {
            File fi = ResourceUtils.getFile("classpath:mail-config.properties");
            properties.load(new InputStreamReader(new FileInputStream(fi)));
            host = properties.getProperty("mailHost");
            port = Integer.parseInt(properties.getProperty("mailPort"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailFrom = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mailTimeout");
            personal = properties.getProperty("mailFrom");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static final String HOST = SendEmailUtil.host;
	private static final Integer PORT = SendEmailUtil.port;
	private static final String USERNAME = SendEmailUtil.userName;
	private static final String PASSWORD = SendEmailUtil.passWord;
	private static final String EmailForm = SendEmailUtil.emailFrom;
	private static final String Timeout = SendEmailUtil.timeout;
	private static final String Personal = SendEmailUtil.personal;
	private static JavaMailSenderImpl mailSender = createMailSender();

	/**
	 * 邮件发送器
	 *
	 * @return 配置好的工具
	 */
	private static JavaMailSenderImpl createMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(HOST);
		sender.setPort(PORT);
		sender.setUsername(USERNAME);
		sender.setPassword(PASSWORD);
		sender.setDefaultEncoding("Utf-8");
		Properties p = new Properties();
		p.setProperty("mail.smtp.timeout", Timeout);
		p.setProperty("mail.smtp.auth", "false");
		sender.setJavaMailProperties(p);
		return sender;
	}

	/**
	 * 发送邮件
	 *
	 * @param to      接受人
	 * @param subject 主题
	 * @param html    发送内容
	 * @throws MessagingException           异常
	 * @throws UnsupportedEncodingException 异常
	 */
	public static void sendMail(String to, String cc, String bcc, String subject, String html)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setFrom(EmailForm, Personal);
		mimeMessage.setRecipients(Message.RecipientType.TO, to);
		mimeMessage.setRecipients(Message.RecipientType.CC, cc);
		mimeMessage.setRecipients(Message.RecipientType.BCC, bcc);
		messageHelper.setSubject(subject);
		messageHelper.setText(html, true);
		mailSender.send(mimeMessage);
	}

	public static void main(String[] args) {
		/*
		 * // 发送消息 Transport.send(message);
		 * System.out.println("Sent message successfully...."); }catch
		 * (MessagingException mex) { mex.printStackTrace(); }
		 */
	}



}
