package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.EventDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.EventService;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */
@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventDao eventDao;
    
    @Autowired
    UserDao userDao;
    
    Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    
    @Override
    public void deleteEvent(Event event) {
        eventDao.delete(event);
    }
    @Override
    public void saveEvent(Event event){
        eventDao.save(event);
    }

    @Override
    public void createEvent(String authorName, Project project, String description, String link, int type) {
        logger.debug("Creating new event on project " + project.getName());
        Event event = new Event();
        event.setDateEvent(new Date());
        event.setType(type);
        Collection<User> participants = project.getAuthorizedUsers();

        for(User user : participants){
            user.getEvents().add(event);
            event.getListeners().add(user);
        }
        //add admin as a listener to event
        User admin = userDao.getByName("admin");
        admin.getEvents().add(event);
        event.getListeners().add(admin);
        // -------------------------------------
        
        event.setDescription(description);
        event.setLink(link);
        this.saveEvent(event);
        logger.debug("New event saved");
    }
    
    @Override
     public Collection<Event> getEventsForUser(String username) {
        User user = userDao.getByName(username);
        Collection<Event> events = user.getEvents();
        for(Event e : events){
            user.getEvents().add(e);
        }
        return events;
    }
     
    @Override
    public void removeEventFromUser(String username, Long eventId){
        logger.debug("User: " +username);
        User user = userDao.getByName(username);
        Event event = eventDao.getById(eventId);
        event.getListeners().remove(user);
        user.getEvents().remove(event);
    }

    @Override
    public void removeAllEventsFromUser(String username) {
        
        User user = userDao.getByName(username);
        Collection<Event> userEvents = user.getEvents();
        for(Event e : userEvents){
            e.getListeners().remove(user);
        }                                                                                                                               
        user.getEvents().removeAll(userEvents);
        logger.debug("Udalosti precteny uzivatelem " + user.getName());
    }
    
   
    
    public Map getEventsCountForUser(String username){
        User user = userDao.getByName(username);
         Collection<Event> events = user.getEvents();
         int commentCount = 0;
         int requirementCount = 0;
         int projectCount = 0;
         for(Event e : events){
             if(!e.isViewed()){
                 switch(e.getType()){
                 case Event.NEW_COMMENT: commentCount++; break;
                 case Event.NEW_REQUIREMENT: requirementCount++;break;
                 case Event.PROJECT_UPDATE: projectCount++;break;
                }
             }
         }
         Map<String,Integer> countMap = new HashMap<String,Integer>();
         countMap.put("commentCount", commentCount);
         countMap.put("projectCount", projectCount);
         countMap.put("requirementCount", requirementCount);
         return countMap;
    }
    

}
