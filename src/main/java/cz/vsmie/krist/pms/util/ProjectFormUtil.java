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
    private User user;
    private String note;
    
    public void resolveProjectFormAction(Project project, User user, String formAction){
        logger.debug("Form action: " + formAction);
        boolean next = false;
        this.project = project;
        this.user = user;
        
        
        if("place".equals(formAction)){
            //send to test
            logger.debug("Executing: 'place'");
            this.setProjectPhase(Phase.PHASE_PLACED);
            this.buildNote("Projekt byl zadán.");
        }
        
         if("appraise".equals(formAction)){
            //send to appraised
            logger.debug("Executing: 'appraise'");
            this.setProjectPhase(Phase.PHASE_APPRAISED);
            this.buildNote("Projekt byl oceněn na " + project.getBudget() +" Kč.");
        }
         
         if("reprice".equals(formAction)){
            logger.debug("Executing: 'reprice'");
            this.setProjectPhase(Phase.PHASE_PLACED);
            this.buildNote("Byla poslána žádost o přecenění.");
        }
         
         if("approve".equals(formAction)){
            //send to approved
            logger.debug("Executing: 'approve'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
            this.buildNote("Projekt byl schválen.");
        }
        
        if("realise".equals(formAction)){
            //send to test
            logger.debug("Executing: 'realise'");
            this.setProjectPhase(Phase.PHASE_REALISED);
            this.buildNote("Projekt byl odeslán k testování.");
        }
        
        if("tested".equals(formAction)){
            logger.debug("Executing: 'tested'");
            this.setProjectPhase(Phase.PHASE_TESTED);
            this.buildNote("Projekt byl označen jako otestovaný.");
        }
        
        if("test_to_approved".equals(formAction)){
            logger.debug("Executing: 'test_to_approved'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
            this.buildNote("Byla nahlášena chyba, projekt byl vrácen zpět k realizaci.");
        }
        
        if("deploy".equals(formAction)){
            logger.debug("Executing: 'deploy'");
            this.setProjectPhase(Phase.PHASE_DEPLOYED);
            this.buildNote("Projekt byl nasazen.");
        }
        
        if("deploy_to_realised".equals(formAction)){
            logger.debug("Executing: 'deploy_to_realised'");
            this.setProjectPhase(Phase.PHASE_REALISED);
            this.buildNote("Projekt byl vrácen zpět k testování.");
        }
        
        if("deploy_to_approved".equals(formAction)){
            logger.debug("Executing: 'deploy_to_approved'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
            this.buildNote("Projekt byl vrácen zpět k přepracování.");
        }
        
        if("accept_complaint".equals(formAction)){
            logger.debug("Executing: 'accept_complaint'");
            this.setProjectPhase(Phase.PHASE_APPROVED);
            this.buildNote("Schválena reklamace, projekt byl odeslán do fáze realizace.");
        }
        if("reject_complaint".equals(formAction)){
            logger.debug("Executing: 'reject_complaint'");
            this.setProjectPhase(Phase.PHASE_DEPLOYED);
            this.buildNote("Reklamace byla zamítnuta.");
        }
        if("complaint".equals(formAction)){
            logger.debug("Executing: 'complaint'");
            this.setProjectPhase(Phase.PHASE_COMPLAINT);
            this.buildNote("Byla podána reklamace.");
        }
       
        projectService.updateProject(this.project, user, note);
        
    }
    
    private void setProjectPhase(Long phaseId){
        this.project.setPhase(projectService.getPhaseById(phaseId));
    }
    
    private void buildNote(String note){
        String buildNote = "";
        buildNote += note + " [uživatel: " + user.getName() + " (ID: " + user.getId() + ")]";
        this.note = buildNote;
    }
    
}
