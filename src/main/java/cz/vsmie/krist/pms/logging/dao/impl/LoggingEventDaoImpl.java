package cz.vsmie.krist.pms.logging.dao.impl;

import cz.vsmie.krist.pms.logging.dao.LoggingEventDao;
import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */

@Repository
public class LoggingEventDaoImpl implements LoggingEventDao{

    @Autowired
    SessionFactory sessionFactory;
    
    public LoggingEvent getLoggingEventById(Long eid) {
        return (LoggingEvent) getCurrentSession().get(LoggingEvent.class, eid);
    }

    public Collection<LoggingEvent> getAllLoggingEvents() {
        return (Collection<LoggingEvent>) getCurrentSession().createCriteria(LoggingEvent.class)
                .addOrder(Order.asc("timestmp")).list();
    }
    
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit){
        return (Collection<LoggingEvent>) getCurrentSession().createCriteria(LoggingEvent.class)
                .addOrder(Order.asc("timestmp"))
                .setFirstResult(offset)
                .setMaxResults(limit).list();
    }

    public void deleteLoggingEvent(Long eid) {
        getCurrentSession().delete(getLoggingEventById(eid));
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
