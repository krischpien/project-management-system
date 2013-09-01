package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.AbstractActiveService;
import cz.vsmie.krist.pms.service.PmsAuditService;
import cz.vsmie.krist.pms.service.PmsMailService;
import cz.vsmie.krist.pms.service.ProjectService;
import cz.vsmie.krist.pms.service.UserService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */

@Service("auditService")
public class PmsAuditServiceImpl extends AbstractActiveService implements PmsAuditService{

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    PmsMailService mailService;
    

    
    Logger logger = LoggerFactory.getLogger(PmsAuditServiceImpl.class);
    
    @Scheduled(cron="0 */60 * * * *")
    @Override
    public void checkProjectsDeadline() {
        if(isActive()){
            logger.info("Running audit of unpaid advances.");
            Collection<Project> unpaidProjects = projectService.getProjectsWithDeadline();
            for(Project project : unpaidProjects){
                Collection<User> participants = project.getAuthorizedUsers();
                for(User user: participants){
                    if(user.getRoles().contains(userService.getRoleById(5L))){ //ROLE_MANAGER
//                        mailService.sendUnpaidAdvancesNotice(project, user);
                        logger.info("Notification has been sent to: " + user.getEmail());
                    }
                }
            }
        }
        else{
            logger.info("Unpaid advances audit is not active");
        }

    }




}
