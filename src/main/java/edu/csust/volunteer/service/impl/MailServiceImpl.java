package edu.csust.volunteer.service.impl;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.model.ApplicationEmail;
import edu.csust.volunteer.service.ImailService;


@Service
public class MailServiceImpl implements ImailService
{
    @Autowired
    private JavaMailSender mailSender;
     
    
    @Autowired
    private TaskExecutor taskExecutor;
    
    
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    
    /**
     * 同步发送邮件
     * 
     * @param email
     * @throws MessagingException
     * @throws IOException
     */
    public void sendMailBySynchronizationMode(ApplicationEmail email) throws MessagingException, IOException { 
         Session session=Session.getDefaultInstance(new Properties());
         MimeMessage mime= new MimeMessage(session);
         MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");    
         helper.setFrom("tamchen7@163.com");//发件人    
         helper.setTo(InternetAddress.parse(email.getAddressee()));//收件人    
         //helper.setBcc("administrator@chinaptp.com");//暗送   
         helper.setReplyTo("tamchen7@163.com");//回复到   
         helper.setSubject(email.getSubject());//邮件主题    
         helper.setText(email.getContent(), true);//true表示设定html格式  
         System.out.println("sendMailBySynchronizationMode");
         mailSender.send(mime);
    }
    
    
    /**
     * 异步发送邮件
     * 
     * @param email
     */
    public void sendMailByAsynchronousMode(final ApplicationEmail email){ 
    	taskExecutor.execute(new Runnable(){    
        public void run(){      
	        try {      
	              sendMailBySynchronizationMode(email);   
	             } catch (Exception e) {    
	            }    
	         }    
    	});   
    }
}

