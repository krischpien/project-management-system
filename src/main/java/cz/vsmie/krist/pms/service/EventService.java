package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Jan Krist
 */
public interface EventService {

    
    /**
     * Remove event from database
     * @param event to remove
     */
    public void deleteEvent(Event event);
    
    /**
     * Save event in database
     * @param event 
     */
    public void saveEvent(Event event);
    
    /**
     * Create event in database
     * @param authorName name of event's author
     * @param project project of event
     * @param description description of event
     * @param link link to event
     * @param type type of event (possible types: project update, new comment, new requirement)
     */
    public void createEvent(String authorName, Project project, String description, String link, int type);
    
    /**
     * Find all events for specified user
     * @param username
     * @return 
     */
    public Collection<Event> getEventsForUser(String username);
    
    /**
     * Get events count for user - count of events by type (map)
     * @param username
     * @return count of events (Map(type, count))
     */
    public Map getEventsCountForUser(String username);
    
    /**
     * Remove all events from user (read all events)
     * @param username 
     */
    public void removeAllEventsFromUser(String username);
    
    /**
     * Remove specified event from user (read specified event)
     * @param username
     * @param eventId 
     */
    public void removeEventFromUser(String username, Long eventId);

    
}
