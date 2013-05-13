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

    
    public void deleteEvent(Event event);
    public void saveEvent(Event event);
    public void craeteEvent(String authorName, Project project, String description, String link, int type);
    public Collection<Event> getEventsForUser(String username);
    public Map getEventsCountForUser(String username);
    public void removeAllEventsFromUser(String username);
    public void removeEventFromUser(String username, Long eventId);

    
}
