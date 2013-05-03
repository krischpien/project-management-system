package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.UserDetails;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface UserService {
    
    void saveUser(UserDetails user) throws UserNameNotAvailable;
    Collection<UserDetails> getAllUsers();
    UserDetails getUserById(long id);
    UserDetails getUserByName(String name);

}
