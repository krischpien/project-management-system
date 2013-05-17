package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dto.Requirement;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */
@Repository
public class RequirementDaoImpl implements RequirementDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Requirement getById(Long id) {
        return (Requirement) getCurrentSession().get(Requirement.class, id);
    }

    @Override
    public Collection<Requirement> getAll() {
        return (Collection<Requirement>) getCurrentSession().createCriteria(Requirement.class).list();
    }

    @Override
    public void save(Requirement requirement) {
        getCurrentSession().save(requirement);
    }

    @Override
    public void update(Requirement requirement) {
        getCurrentSession().update(requirement);
    }

    @Override
    public void delete(Requirement requirement) {
        getCurrentSession().delete(requirement);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
