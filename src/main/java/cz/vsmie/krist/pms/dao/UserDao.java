package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.User;

/**
 *
 * @author Jan Krist
 */

public interface UserDao extends GenericDao<User> {
    
    public User getByName(String name);
    public User getByEmail(String email);

}
