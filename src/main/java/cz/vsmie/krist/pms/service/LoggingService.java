package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.logging.dto.LoggingEvent;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface LoggingService {
    
    /**
     * Vyhledá událost systému dle jejího id
     * @param eid
     * @return udalost
     */
    public LoggingEvent getLoggingEventById(Long eid);
    
    
    /**
     * Vyhledá veškeré události systému v databázi.
     * @return události
     */
    public Collection<LoggingEvent> getAllLoggingEvents();
    
    /**
     * Stránkování událostí systému.
     * @param offset začátek
     * @param limit počet událostí na stránku
     * @return stránka událostí systému
     */
    public Collection<LoggingEvent> getAllLoggingEventsPaginated(int offset, int limit);
    
    
    /**
     * Odstraní událost systému z databáze.
     * @param eid id události
     */
    public void deleteLoggingEvent(Long eid);
    
    /**
     * Odstraní veškeré události systému z databáze.
     */
    public void deleteAllLoggingEvents();
    
    
    /**
     * Získá počet událostí v systému.
     * @return počet systémových událostí
     */
    public Number getLoggingEventCount();
}
