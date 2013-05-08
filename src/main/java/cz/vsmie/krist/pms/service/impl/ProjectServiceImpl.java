package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.ProjectService;
import java.util.Date;
import java.util.Collection;
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
   
    
    Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);


    
    public Project getProjectById(Long id) {
        return projectDao.getById(id);
    }

    public Collection<Project> getAllProjects() {
        return projectDao.getAll();
    }
    
    public Collection<Project> getProjectsOfUser(Long uid) {
        User user = userDao.getById(uid);
        return user.getProjects();
    }

    public void saveProject(Project project) {
        projectDao.save(project);
    }

    public void updateProject(Project project) {
        projectDao.update(project);
    }
    
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }
    
    public void saveComment(Comment comment, Long projectId, String authorName){
        comment.setCreateDate(new Date());
        if(authorName != null){
            logger.info("Author: " + authorName);
            comment.setAuthor(userDao.getByName(authorName));
        }
        else{
            logger.info("Author: null");
        }
        Project persistedProject = projectDao.getById(projectId);
        persistedProject.getComments().add(comment);
    }

    public void saveRequirement(Requirement requirement, Long projectId, String authorName) {
        requirement.setAuthor(userDao.getByName(authorName));
        requirement.setCreateDate(new Date());
        Project project = projectDao.getById(projectId);
        project.getRequirements().add(requirement);
    }

}
