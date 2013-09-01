package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.ProjectHistoryDao;
import cz.vsmie.krist.pms.dto.ProjectHistory;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectHistoryDaoImpl implements ProjectHistoryDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ProjectHistory getById(Long id) {
        return (ProjectHistory) getCurrentSession().get(ProjectHistory.class, id);
    }

    @Override
    public Collection<ProjectHistory> getAll() {
        return (Collection<ProjectHistory>) getCurrentSession().createCriteria(ProjectHistory.class).addOrder(Order.asc("dateChange")).list();
    }

    @Override
    public void save(ProjectHistory entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(ProjectHistory entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(ProjectHistory entity) {
        getCurrentSession().delete(entity);
    }
    
    
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    


}
