package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface LoggingService {
    
    /**
     * Look up logging event by event id
     * @param eid
     * @return logging event
     */
    public LoggingEvent getLoggingEventById(Long eid);
    
    
    /**
     * Look up all logging events in database.
     * @return události
     */
    public Collection<LoggingEvent> getAllLoggingEvents();
    
    /**
     * Pagination of all logging events.
     * @param offset start
     * @param limit number of events on one page
     * @return one page of logging events
     */
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit);
    
    
    /**
     * Remove logging event from database.
     * @param eid logging event id
     */
    public void deleteLoggingEvent(Long eid);
    
    /**
     * Remove all logging events from database.
     */
    public void deleteAllLoggingEvents();
    
    
    /**
     * Get count of all logging events from database.
     * @return count of all logging events
     */
    public Number getLoggingEventCount();
}
