package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.PmsActiveService;
import cz.vsmie.krist.pms.service.PmsMailService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service("pmsMailService")
public class PmsMailServiceImpl implements PmsMailService{
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    
    private boolean active = true;
    
    private Logger logger = LoggerFactory.getLogger(PmsMailServiceImpl.class);
    

    @Async
    public void sendCreateUserNotice(User user) {
        if(isActive()){
            Map model = new HashMap();
            model.put("user", user);
            String messageContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mailTemplate.vm", "UTF-8", model);
            this.sendPmsMessage(user.getEmail(), "Informační zpráva ze systému Project Management System", messageContent);
        }
    }
    
    @Async
    public void sendCreateProjectNotice(Project project){
        
    }
    
    private void sendPmsMessage(String to, String subject, String text){
        logger.debug("Mail service fired!");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message); // pridat true pro multipart

            try {
                messageHelper.setTo(to);
                messageHelper.setFrom("pmscz.noreply@gmail.com");
                message.setSubject(subject, "UTF-8");
                messageHelper.setText(text,true); //true: content type = 'text/html'
                mailSender.send(message);
            } catch (MessagingException ex) {
                logger.error("Chyba v mailu: " + ex.getMessage());
            }
            catch (MailException ex){
                logger.error("Chyba při odesílání mailu na adresu " + to +". Chyba: " + ex.getMessage());
            }

            logger.debug("Odesílám na: " + to);
    }
    
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
