package cz.vsmie.krist.pms.logging.dao.impl;

import cz.vsmie.krist.pms.logging.dao.LoggingEventDao;
import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
    
    @Override
    public LoggingEvent getLoggingEventById(Long eid) {
        return (LoggingEvent) getCurrentSession().get(LoggingEvent.class, eid);
    }

    @Override
    public Collection<LoggingEvent> getAllLoggingEvents() {
        return (Collection<LoggingEvent>) getCurrentSession().createCriteria(LoggingEvent.class).list();
    }
    
    @Override
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit){
        return (Collection<LoggingEvent>) getCurrentSession().createCriteria(LoggingEvent.class)
                .addOrder(Order.desc("timestmp"))
                .setFirstResult(offset)
                .setMaxResults(limit).list();
    }
    
    @Override
    public Number getLoggingEventCount(){
        return (Number) getCurrentSession().createCriteria(LoggingEvent.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void deleteLoggingEvent(Long eid) {
        getCurrentSession().delete(getLoggingEventById(eid));
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
