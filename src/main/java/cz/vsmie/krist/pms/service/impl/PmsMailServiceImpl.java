package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.AbstractActiveService;
import cz.vsmie.krist.pms.service.PmsMailService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Jan Krist
 */

@Service("mailService")
public class PmsMailServiceImpl extends AbstractActiveService implements PmsMailService{
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    MessageSource messageSource;
    
    private Logger logger = LoggerFactory.getLogger(PmsMailServiceImpl.class);

    
    
    @Override
    @Async
    public void sendCreateUserNotice(User user) {
        if(isActive()){
            logger.debug("Sending create user notice to " + user.getEmail());
            Map model = new HashMap();
            model.put("user", user);
            String messageContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/userNoticeTemplate.vm", "UTF-8", model);
            this.sendPmsMessage(user.getEmail(), "Informační zpráva ze systému Project Management System", messageContent);
        }
    }
    
    @Override
    @Async
    public void sendCreateProjectNotice(Project project){
        if(isActive()){
        Map model = new HashMap();
        model.put("project", project);
        for(User user : project.getAuthorizedUsers()){
            logger.debug("Sending notice about new project to " + user.getEmail());
            String messageContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/projectNoticeTemplate.vm", "UTF-8", model);
            this.sendPmsMessage(user.getEmail(), "Upozornění o vytvořeném projektu", messageContent);
            }
        }
    }
    
    @Override
    @Async
    public void sendDeadlineNotice(Project project, User user){
        if(isActive()){
            Map model = new HashMap();
            model.put("project", project);
            String messageContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/deadlineNotice.vm", "UTF-8", model);
            this.sendPmsMessage(user.getEmail(), "Upozornění o prošlém termínu projektu", messageContent);
        }
    }
    
    
    
    
    @Async
    private void sendPmsMessage(String to, String subject, String text){
        logger.debug("Sending message.");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message); // pridat true pro multipart

            try {
                messageHelper.setTo(to);
                messageHelper.setFrom("pmscz.noreply@gmail.com");
                message.setSubject(subject, "UTF-8");
                messageHelper.setText(text,true); //true: content type = 'text/html'
                logger.info("Sending mail to " + to);
                mailSender.send(message);
                //logger.debug("Mail OFF, CHANGE!");
            } catch (MessagingException ex) {
                logger.error("Sending mail to " + to +" failed. Error: " + ex.getMessage());
            }
            catch (MailException ex){
                logger.error("Sending mail to " + to +" failed. Error: " + ex.getMessage());
            }

            logger.debug("Sending mail to: " + to +" done");
    }
    
    

}
