package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.UserDetails;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */

public interface UserDao {
    
    public Collection<UserDetails> getAllUsers();
    public void saveUser(UserDetails user);
    public UserDetails getUserById(long id);
    public UserDetails getUserByName(String name);

}
