package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.CommentDao;
import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.ProjectService;
import java.util.Collection;
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
        logger.debug("Ukládání projektu " + project.getName());
        project.setDateCreate(new Date());
        project.setPhase(phaseDao.getById(Phase.PHASE_NEW));
        projectDao.save(project);
    }

    public void updateProject(Project project) {
        projectDao.update(project);
    }
    
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }
    
    public void saveComment(Comment comment, Long projectId, String authorName){
        comment.setDateCreate(new Date());
        comment.setAuthor(userDao.getByName(authorName));
        comment.setProject(projectDao.getById(projectId));
        commentDao.save(comment);

    }

    public void saveRequirement(Requirement requirement, Long projectId, String authorName) {
        requirement.setAuthor(userDao.getByName(authorName));
        requirement.setDateCreate(new Date());
        Project project = projectDao.getById(projectId);
        requirement.setProject(project);
        requirementDao.save(requirement);
    }

}
