package cz.vsmie.krist.pms.dao;

import java.util.Collection;



/**
 *
 * @author Jan Krist
 */

public interface GenericDao<Entity> {
    /**
     * Seaarch for entity by its id
     * @param id
     * @return entity
     */
    public Entity getById(Long id); 
    
    /**
     * Get all entities in database
     * @return all entities
     */
    public Collection<Entity> getAll();
    
    /**
     * Save entity in database
     * @param entity 
     */
    public void save(Entity entity);
    
    /**
     * Update entity in database
     * @param entity 
     */
    public void update(Entity entity);
    
    /**
     * Remove entity from database
     * @param entity 
     */
    public void delete(Entity entity);

}
