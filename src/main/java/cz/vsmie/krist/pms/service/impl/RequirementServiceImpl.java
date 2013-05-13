package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.RequirementService;
import java.util.Date;
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
    EventService eventService;
    
    
    public Requirement getRequirementById(Long id) {
        return requirementDao.getById(id);
    }

    public void saveRequirement(Requirement requirement, Long projectId, String authorName) {
        requirement.setDateCreate(new Date());
        Project project = projectDao.getById(projectId);
        requirement.setProject(project);
        requirementDao.save(requirement);
        String link = "/project/"+project.getId()+"-project/requirement/details/"+requirement.getId();
        eventService.craeteEvent(authorName, project, "Byl vytvořen nový požadavek k projektu " + project.getName() + ", uživatelem "+authorName, link, Event.NEW_REQUIREMENT);
    }
    
    

}
