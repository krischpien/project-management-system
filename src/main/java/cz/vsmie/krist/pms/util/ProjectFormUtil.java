package cz.vsmie.krist.pms.util;

import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Krist
 */
@Component
public class ProjectFormUtil {
    
    @Autowired
    ProjectService projectService;

    private static Logger logger = LoggerFactory.getLogger(ProjectFormUtil.class);
    
    private Project project;
    
    public void resolveProjectFormAction(Project project, User user, String formAction){
        logger.debug("Form action: " + formAction);
        boolean next = false;
        this.project = project;
        
        if("place".equals(formAction)){
            //send to test
            logger.debug("Executing: 'place'");
            this.setProjectPhase(Phase.PHASE_PLACED);
        }
        
         if("appraise".equals(formAction)){
            //send to test
            logger.debug("Executing: 'appraise'");
            this.setProjectPhase(Phase.PHASE_APPRAISED);
        }
         
         if("reprice".equals(formAction)){
            logger.debug("Executing: 'reprice'");
            this.setProjectPhase(Phase.PHASE_PLACED);
        }
         
         if("approve".equals(formAction)){
            //send to test
            logger.debug("Executing: 'approve'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
        }
        
        if("realise".equals(formAction)){
            //send to test
            logger.debug("Executing: 'realise'");
            this.setProjectPhase(Phase.PHASE_REALISED);
        }
        
        if("tested".equals(formAction)){
            logger.debug("Executing: 'tested'");
            this.setProjectPhase(Phase.PHASE_TESTED);
            next = true;
        }
        
        if("test_to_approved".equals(formAction)){
            logger.debug("Executing: 'test_to_approved'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
        }
        
        if("deploy".equals(formAction)){
            logger.debug("Executing: 'deploy'");
            this.setProjectPhase(Phase.PHASE_DEPLOYED);
            next = true;
        }
        
        if("deploy_to_realised".equals(formAction)){
            logger.debug("Executing: 'deploy_to_realised'");
            this.setProjectPhase(Phase.PHASE_REALISED);
        }
        
        if("deploy_to_approved".equals(formAction)){
            logger.debug("Executing: 'deploy_to_approved'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
        }
        
        if("accept_complaint".equals(formAction)){
            logger.debug("Executing: 'accept_complaint'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
        }
        if("reject_complaint".equals(formAction)){
            logger.debug("Executing: 'reject_complaint'");
            this.setProjectPhase(Phase.PHASE_DEPLOYED);
        }
        if("complaint".equals(formAction)){
            logger.debug("Executing: 'complaint'");
            this.setProjectPhase(Phase.PHASE_COMPLAINT);
            next = true;
        }
       
        projectService.updateProject(this.project, next, user);
        
    }
    
    private void setProjectPhase(Long phaseId){
        this.project.setPhase(projectService.getPhaseById(phaseId));
    }
    
}
