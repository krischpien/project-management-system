package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.RequirementDao;
import cz.vsmie.krist.pms.dto.Requirement;
import java.util.Set;
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

    public Requirement getById(Long id) {
        return (Requirement) getCurrentSession().get(Requirement.class, id);
    }

    public Set<Requirement> getAll() {
        return (Set<Requirement>) getCurrentSession().createCriteria(Requirement.class).list();
    }

    public void save(Requirement requirement) {
        getCurrentSession().save(requirement);
    }

    public void update(Requirement requirement) {
        getCurrentSession().update(requirement);
    }

    public void delete(Requirement requirement) {
        getCurrentSession().delete(requirement);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
