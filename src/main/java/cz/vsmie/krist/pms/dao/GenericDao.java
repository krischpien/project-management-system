package cz.vsmie.krist.pms.dao;

import java.util.Collection;



/**
 *
 * @author Jan Krist
 */

public interface GenericDao<Entity> {
    
    public Entity getById(Long id); 
    public Collection<Entity> getAll();
    public void save(Entity entity);
    public void update(Entity entity);
    public void delete(Entity entity);

}
