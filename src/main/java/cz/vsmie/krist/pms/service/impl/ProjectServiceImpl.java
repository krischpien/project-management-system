package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.CommentDao;
import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.ProjectService;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    
    @Autowired
    ProjectDao projectDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    RequirementDao requirementDao;
    @Autowired
    PhaseDao phaseDao;
    
    @Autowired
    EventService eventService;
    
    Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);


    
    public Project getProjectById(Long id) {
        return projectDao.getById(id);
    }

    public Collection<Project> getAllProjects() {
        return projectDao.getAll();
    }
    
    public Collection<Project> getProjectsOfUser(String username) {
        User user = userDao.getByName(username);
        Collection<Project> userProjects = new HashSet<Project>();
        Collection<Project> allProjects = projectDao.getAll();
        for(Project p : allProjects){
            if(p.getAuthorizedUsers().contains(user)){
                userProjects.add(p);
            }
        }
        return userProjects;
    }
    
    public Collection<Project> getProjectsWithUnpaidAdvances(){
        return projectDao.getProjectWithUnpaidAdvances();
    }

    public void saveProject(Project project) {
        logger.debug("Ukládání projektu " + project.getName());
        project.setDateCreate(new Date());
        project.setPhase(phaseDao.getById(Phase.PHASE_NEW));
        projectDao.save(project);
    }

    public void updateProject(Project project, User updater) {
        projectDao.update(project);
        String link = "/project/details/"+project.getId()+"-"+project.getName();
        eventService.craeteEvent(updater.getName(), project, "Změna v projektu " + project.getName() + " ("+ updater.getName() +")", link, Event.PROJECT_UPDATE);

    }
    
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }
    
    public void deleteProjectById(Long pid){
        Project project = projectDao.getById(pid);
        for(User user : project.getAuthorizedUsers()){
            user.getProjects().remove(project);
        }
        projectDao.delete(project);
    }
    
    public void saveComment(Comment comment, Long projectId, String authorName){
        Project project = projectDao.getById(projectId);
        User author = userDao.getByName(authorName);
        comment.setDateCreate(new Date());
        comment.setAuthor(author);
        comment.setProject(project);
        commentDao.save(comment);
        String link = "/project/details/"+project.getId()+"-projekt";
        eventService.craeteEvent(authorName, project, "Nový komentář k projektu " + project.getName() + " ("+ authorName +")", link, Event.NEW_COMMENT);
    }

    public boolean checkUserPermissionToProject(String username, Project project) {
        logger.debug("Zjistuji opravneni na projekt");
        User user = userDao.getByName(username);
//        Project project = projectDao.getById(projectId);
        boolean authorized = project.getAuthorizedUsers().contains(user);
        logger.debug("Uzivatel " + user.getName() + " opravnen k projektu " + project.getName() + ": " + authorized);
        return authorized;
    }
    
    

    

}
