package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.EventDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.User;
import java.util.Collection;
import org.hibernate.Criteria;
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
public class EventDaoImpl implements EventDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event getById(Long id) {
        return (Event) getCurrentSession().get(Event.class, id);
    }

    @Override
    public Collection<Event> getAll() {
        return (Collection<Event>) getCurrentSession().createCriteria(Event.class).list();
    }

    @Override
    public void save(Event event) {
        getCurrentSession().save(event);
    }

    @Override
    public void update(Event event) {
        getCurrentSession().update(event);
    }

    @Override
    public void delete(Event event) {
        getCurrentSession().delete(event);
    }
    
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    

}
