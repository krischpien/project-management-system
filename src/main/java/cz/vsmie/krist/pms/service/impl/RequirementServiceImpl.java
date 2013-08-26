package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.RequirementService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */
@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    RequirementDao requirementDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PhaseDao phaseDao;
    @Autowired
    EventService eventService;
    
    Logger logger = LoggerFactory.getLogger(RequirementServiceImpl.class);
    
    
    
    @Override
    public Requirement getRequirementById(Long id) {
        return requirementDao.getById(id);
    }

    @Override
    public void saveRequirement(Requirement requirement, Long projectId, String authorName) {
        requirement.setDateCreate(new Date());
        requirement.setPhase(phaseDao.getById(Phase.PHASE_NEW));
        Project project = projectDao.getById(projectId);
        requirement.setProject(project);
        requirementDao.save(requirement);
        String link = "/project/"+project.getId()+"-project/requirement/details/"+requirement.getId();
        logger.info("New requirement has been created on project " + project.getName());
        eventService.createEvent(authorName, project, "New requirement has been created on project " + project.getName() + ", by user "+authorName, link, Event.NEW_REQUIREMENT);
    }
    
    

}
