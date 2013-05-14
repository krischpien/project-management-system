package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface LoggingService {
    public LoggingEvent getLoggingEventById(Long eid);
    public Collection<LoggingEvent> getAllLoggingEvents();
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit);
    public void deleteLoggingEvent(Long eid);
    public Number getLoggingEventCount();
}
