package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.PhaseDao;
import cz.vsmie.krist.pms.dto.Phase;
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
public class PhaseDaoImpl implements PhaseDao{
    
    @Autowired
    SessionFactory sessionFactory;

    public Phase getById(Long id) {
        return (Phase) getCurrentSession().get(Phase.class, id);
    }

    public Set<Phase> getAll() {
        return (Set<Phase>) getCurrentSession().createCriteria(Phase.class).list();
    }

    public void save(Phase phase) {
        getCurrentSession().save(phase);
    }

    public void update(Phase phase) {
        getCurrentSession().update(phase);
    }

    public void delete(Phase phase) {
        getCurrentSession().delete(phase);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
