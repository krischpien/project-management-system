package cz.vsmie.krist.pms.logging.dao;

import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface LoggingEventDao {
    
    public LoggingEvent getLoggingEventById(Long eid);
    public Collection<LoggingEvent> getAllLoggingEvents();
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit);
    public Number getLoggingEventCount();
    public void deleteLoggingEvent(Long eid);

}
