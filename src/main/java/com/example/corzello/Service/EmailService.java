package com.example.corzello.Service;


import com.example.corzello.Entity.EmailTemplateName;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Repository.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;


@Service
public class EmailService {
@Autowired
    UserRepo userRepo ;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine ;
    @Async
    public void sendEmail(
            String to,
            String username,
            String subject,
            EmailTemplateName emailTemplate,
            String confirmationUrl) throws MessagingException {
        String templateName ;
        if (emailTemplate==null){
            templateName="confirm-email";
        }else {
            templateName=emailTemplate.name();
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        Map<String,Object> properties = new HashMap<>();
        properties.put("username",username);
        properties.put("confirmationUrl",confirmationUrl);
        Context context =new Context();
        context.setVariables(properties);
        try {
            helper.setFrom("zaibiahmed4666@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.addInline("logo", new ClassPathResource("../img/COURZELOLOGO.jpg"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String template = templateEngine.process(templateName,context);
        helper.setText(template,true);
        javaMailSender.send(message);
    }
    @Async
    public void sendPasswordEmail(String email) throws MessagingException {
        String templateName ="forgot-password";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        UserEntity user = userRepo.findByEmail(email);
        String username = user.fullname();
        Map<String,Object> properties = new HashMap<>();
        properties.put("username",username);
        Context context =new Context();
        context.setVariables(properties);
        try {
            helper.setFrom("zaibiahmed4666@gmail.com");
            helper.setTo(email);
            helper.setSubject("forgot password");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String template = templateEngine.process(templateName,context);
        helper.setText(template.formatted(email),true);
        javaMailSender.send(message);
    }
    @Async
    public void sendemailrecrutement(String email) throws MessagingException {
        String templateName ="recrutement";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        UserEntity user = userRepo.findByEmail(email);
        String username = user.fullname();
        Map<String,Object> properties = new HashMap<>();
        properties.put("username",username);
        Context context =new Context();
        context.setVariables(properties);
        try {
            helper.setFrom("zaibiahmed4666@gmail.com");
            helper.setTo(email);
            helper.setSubject("recrutement add");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String template = templateEngine.process(templateName,context);
        helper.setText(template.formatted(email),true);
        javaMailSender.send(message);
    }
}