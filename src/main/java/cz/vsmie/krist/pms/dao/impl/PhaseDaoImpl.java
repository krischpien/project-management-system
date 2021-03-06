package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dto.Phase;
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
public class PhaseDaoImpl implements PhaseDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Phase getById(Long id) {
        return (Phase) getCurrentSession().get(Phase.class, id);
    }

    @Override
    public Collection<Phase> getAll() {
        return (Collection<Phase>) getCurrentSession().createCriteria(Phase.class).list();
    }

    @Override
    public void save(Phase phase) {
        getCurrentSession().save(phase);
    }

    @Override
    public void update(Phase phase) {
        getCurrentSession().update(phase);
    }

    @Override
    public void delete(Phase phase) {
        getCurrentSession().delete(phase);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
