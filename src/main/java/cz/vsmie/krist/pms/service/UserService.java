package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
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
    
    public Collection<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByName(String name);
    public Collection<UserRole> getAllRoles(boolean assignable);
    public void saveUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable;
    public void updateUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable;
    public void deleteUser(User user);
    public UserRole getRoleById(Long id);
    

}
