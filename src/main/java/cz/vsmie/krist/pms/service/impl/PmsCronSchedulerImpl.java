package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.PmsCronScheduler;
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

@Service("pmsCronSchedulerService")
public class PmsCronSchedulerImpl implements PmsCronScheduler{

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    PmsMailService mailService;
    
    private boolean active = true;
    
    Logger logger = LoggerFactory.getLogger(PmsCronSchedulerImpl.class);
    
//    @Scheduled(cron="0 * * * * *")
    public void checkUnpaidAdvances() {
        if(isActive()){
            logger.info("Probíhá naplánovaná úloha");
            Collection<Project> unpaidProjects = projectService.getProjectsWithUnpaidAdvances();
            for(Project project : unpaidProjects){
                Collection<User> participants = project.getAuthorizedUsers();
                for(User user: participants){
                    if(user.getRoles().contains(userService.getRoleById(5L))){ //ROLE_MANAGER
//                        mailService.sendUnpaidAdvancesNotice(project, user);
                        logger.debug("Posílám upozornění na: " + user.getEmail());
                    }
                }
            }
        }
        else{
            logger.debug("Plánovač není aktivní");
        }

    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
