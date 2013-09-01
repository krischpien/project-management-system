package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.CommentDao;
import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.ProjectHistoryDao;
import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.ProjectHistory;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.PmsMailService;
import cz.vsmie.krist.pms.service.ProjectService;
import java.sql.Timestamp;
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
    ProjectHistoryDao projectHistoryDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    RequirementDao requirementDao;
    @Autowired
    PhaseDao phaseDao;
    @Autowired
    PmsMailService mailService;
    @Autowired
    EventService eventService;
    
    Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);


    
        
    @Override
    public Project getProjectById(Long id) {
        return projectDao.getById(id);
    }

    @Override
    public Collection<Project> getAllProjects() {
        return projectDao.getAll();
    }
    
    @Override
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
    
    @Override
        public Collection<Project> getProjectsWithDeadline(){
        return projectDao.getProjectWithDeadline();
    }

    @Override
    public void saveProject(Project project) {
        logger.debug("Saving project " + project.getName());
        project.setDateCreate(new Date());
        project.setPhase(phaseDao.getById(Phase.PHASE_NEW));
        
        // create first point of history
        ProjectHistory history = new ProjectHistory();
        history.setDateChange(new Timestamp(new Date().getTime()));
        history.setNote("Vytvořeno");
        history.setProject(project);
        logger.debug("Creating history of project " + project.getName());
        projectHistoryDao.save(history);
        mailService.sendCreateProjectNotice(project);
        project.getProjectHistory().add(history);
        projectDao.save(project);
        logger.info("Saved new project: " + project.getName());
    }

    @Override
    public void updateProject(Project project, User updater, String note) {
        // create history point
        ProjectHistory history = new ProjectHistory();
        history.setDateChange(new Timestamp(new Date().getTime()));
        history.setNote(note);
        history.setProject(project);
        logger.debug("Updating history of project " + project.getName());
        projectHistoryDao.save(history);
        
        project.getProjectHistory().add(history);

        projectDao.update(project);
        String link = "/project/edit/edit.do?pid="+project.getId();
        eventService.createEvent(updater.getName(), project, "Změna v projektu " + project.getName() + " ("+ updater.getName() +")", link, Event.PROJECT_UPDATE);
    }
    
    @Override
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }
    
    @Override
    public void deleteProjectById(Long pid){
        Project project = projectDao.getById(pid);
        for(User user : project.getAuthorizedUsers()){
            user.getProjects().remove(project);
        }
        projectDao.delete(project);
        logger.info("Removed project " + project.getName());
    }
    
    @Override
    public void saveComment(Comment comment, Long projectId, String authorName){
        Project project = projectDao.getById(projectId);
        User author = userDao.getByName(authorName);
        comment.setDateCreate(new Date());
        comment.setAuthor(author);
        comment.setProject(project);
        commentDao.save(comment);
        String link = "/project/edit/edit.do?pid="+project.getId();
        logger.info("Saved new comment " + project.getName());
        eventService.createEvent(authorName, project, "Nový komentář v projektu " + project.getName() + " ("+ authorName +")", link, Event.NEW_COMMENT);
    }
    
    

    @Override
    public boolean checkUserPermissionToProject(String username, Project project) {
        logger.debug("Checking permissions on " + project.getName());
        User user = userDao.getByName(username);
//        Project project = projectDao.getById(projectId);
        boolean authorized = project.getAuthorizedUsers().contains(user) && !username.equals("admin");
        logger.debug("User " + user.getName() + " is authorized to acces " + project.getName() + ": " + authorized);
        return authorized;
    }

    @Override
    public Collection<Phase> getAllPhases() {
        return phaseDao.getAll();
    }

    @Override
    public Phase getPhaseById(Long phaseId) {
        return phaseDao.getById(phaseId);
    }
    
    

    

}
