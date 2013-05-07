package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.RoleDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
   
    public Collection<User> getAllUsers(){
        return userDao.getAll();
    }
    
    public void saveUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable{
        logger.info("Lookin' for " + user.getName());
        if(userDao.getByName(user.getName())!= null){
            logger.info("Uživatel " + user.getName() + "je již v databázi");
            throw new UserNameNotAvailable(user.getName());
        }
        if(userDao.getByEmail(user.getEmail()) != null){
            logger.info("Emailova adresa " + user.getEmail() + "je již v databázi");
            throw new UserEmailNotAvailable(user.getEmail());
        }
        else{
            userDao.save(user);
        }
    }

    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    public User getUserByName(String name) {
        return userDao.getByName(name);
    }

    public Collection<UserRole> getAllRoles(boolean assignable) {
        if(assignable){
            return roleDao.getAssignableRoles();
        }
        return roleDao.getMainRoles();
    }

    public UserRole getRoleById(Long id) {
        return roleDao.getById(id);
    }

    public void deleteUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateUser(User updatedUser) throws UserNameNotAvailable, UserEmailNotAvailable {
        User userByName = userDao.getByName(updatedUser.getName());
        User userByEmail = userDao.getByEmail(updatedUser.getEmail());
        if(userByName != null && !userByName.equals(updatedUser)){
            throw new UserNameNotAvailable(updatedUser.getName());
        }
        if(userByEmail != null && !userByEmail.equals(updatedUser)){
            throw new UserEmailNotAvailable(updatedUser.getEmail());
        }
        userDao.update(updatedUser);
    }
    
    
    

 
    
    

}
