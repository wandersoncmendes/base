package com.base.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;


    public void prepareAndSend(String title, String body, String nome, String email, String newPassword) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email);
            messageHelper.setSubject(title);
            String content = mailContentBuilder.build(nome, newPassword, body);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
            logger.error("Error", e);
            // runtime exception; compiler will not force you to handle it
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            // runtime exception; compiler will not force you to handle it
        }
    }

    /**
     * Email generico
     * @param title
     * @param body
     * @param nome
     * @param email
     * @param url
     */
    public void prepareAndSendLink(String title, String preHeader, String body, String nome, String email, String url) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email);
            messageHelper.setSubject(title);
            String content = mailContentBuilder.buildLink(title, preHeader, nome, url, body);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
            logger.error("Error", e);
            // runtime exception; compiler will not force you to handle it
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            // runtime exception; compiler will not force you to handle it
        }
    }

}
