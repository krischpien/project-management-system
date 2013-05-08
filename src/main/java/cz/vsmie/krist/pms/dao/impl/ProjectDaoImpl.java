package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.ProjectDao;
import cz.vsmie.krist.pms.dto.Project;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    public Project getById(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(Project.class).add(Restrictions.idEq(id));
//                .setFetchMode("comments", FetchMode.JOIN);
        return (Project) criteria.uniqueResult();
    }

    public Collection<Project> getAll() {
        return (Collection<Project>) getCurrentSession().createCriteria(Project.class).list();
    }

    public void save(Project project) {
        getCurrentSession().save(project);
    }

    public void update(Project project) {
        getCurrentSession().update(project);
    }

    public void delete(Project project) {
        getCurrentSession().delete(project);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
