package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.logging.dao.LoggingEventDao;
import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import cz.vsmie.krist.pms.service.LoggingService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */

@Service
public class LoggingServiceImpl implements LoggingService{
    @Autowired
    LoggingEventDao loggingEventDao;
    
    
    @Override
    public LoggingEvent getLoggingEventById(Long eid) {
        return loggingEventDao.getLoggingEventById(eid);
    }

    @Override
    public Collection<LoggingEvent> getAllLoggingEvents() {
        return loggingEventDao.getAllLoggingEvents();
    }
    @Override
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit){
        return loggingEventDao.getAllLoggingEventsPaginated(offset, limit);
    }
    @Override
    public Number getLoggingEventCount(){
        return loggingEventDao.getLoggingEventCount();
    }

    @Override
    public void deleteLoggingEvent(Long eid) {
        loggingEventDao.deleteLoggingEvent(eid);
    }
    @Override
    public void deleteAllLoggingEvents(){
        loggingEventDao.deleteAllLoggingEvents();
    }

}
