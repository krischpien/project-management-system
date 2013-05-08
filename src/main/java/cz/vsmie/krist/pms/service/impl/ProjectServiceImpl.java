package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.ProjectService;
import java.util.Collection;
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


}
