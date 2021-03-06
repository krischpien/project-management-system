package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dto.Project;
import java.util.Collection;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */

@Repository
public class ProjectDaoImpl implements ProjectDao{
    
    @Autowired
    SessionFactory sessionFactory;
    
    Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);

    @Override
    public Project getById(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(Project.class).add(Restrictions.idEq(id));
//                .setFetchMode("comments", FetchMode.JOIN);
        return (Project) criteria.uniqueResult();
    }

    @Override
    public Collection<Project> getAll() {
        return (Collection<Project>) getCurrentSession().createCriteria(Project.class).list();
    }
    
    @Override
    public Collection<Project> getProjectWithDeadline(){
        logger.debug("Searching for projects with unpaid advances.");
        Criteria criteria = getCurrentSession().createCriteria(Project.class);
        criteria.add(Restrictions.gt("dateDeadline", new Date())); 
        criteria.add(Restrictions.ge("phase.id", 1L)).list(); // look for project advanced phases
        Collection<Project> unpaidProjects = (Collection<Project>) criteria.list();
                
        logger.debug("Found " + unpaidProjects.size() + " projects with unpaid advances.");
        return unpaidProjects;
    }


    @Override
    public void save(Project project) {
        getCurrentSession().save(project);
    }

    @Override
    public void update(Project project) {
        getCurrentSession().update(project);
    }

    @Override
    public void delete(Project project) {
        // all relations must be manually removed from this side - other side will be removed by setting orphanRemoval on DTO
        project.getAuthorizedUsers().clear();
        project.getComments().clear();
        project.getProjectHistory().clear();
        project.getRequirements().clear();
        getCurrentSession().delete(project);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
