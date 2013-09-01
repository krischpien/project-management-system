package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.User;

/**
 *
 * @author Jan Krist
 */

public interface UserDao extends GenericDao<User> {
    
    /**
     * Find user in database by his name
     * @param name of user
     * @return user
     */
    public User getByName(String name);
    
    /**
     * Find user in database by his email address
     * @param email of user
     * @return user
     */
    public User getByEmail(String email);

}
