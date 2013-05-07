package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface UserService {
    
    Collection<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String name);
    Collection<UserRole> getAllRoles(boolean assignable);
    void saveUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable;
    void updateUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable;
    void deleteUser(User user);
    UserRole getRoleById(Long id);

}
