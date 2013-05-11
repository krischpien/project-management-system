package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.RoleDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.PmsMailService;
import cz.vsmie.krist.pms.service.UserService;
import java.util.Collection;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PmsMailService mailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
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
            encodePassword(user);
            userDao.save(user);
            mailService.sendCreateUserNotice(user); //async
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
        userDao.delete(user);
    }

    public void updateUser(User updatedUser, boolean encodePassword) throws UserNameNotAvailable, UserEmailNotAvailable {
        User userByName = userDao.getByName(updatedUser.getName());
        User userByEmail = userDao.getByEmail(updatedUser.getEmail());
        if(userByName != null && !userByName.equals(updatedUser)){
            throw new UserNameNotAvailable(updatedUser.getName());
        }
        if(userByEmail != null && !userByEmail.equals(updatedUser)){
            throw new UserEmailNotAvailable(updatedUser.getEmail());
        }
        if(encodePassword){
            encodePassword(updatedUser);
        }
        userDao.update(updatedUser);
    }
    
    public void updateLastLogin(String username, String hostIp){
        User loggedUser = userDao.getByName(username);
        loggedUser.setLastIp(hostIp);
        loggedUser.setLastLogin(new Date());
    }
    
    private void encodePassword(User user){
        String plainPassword = user.getPassword();
        String cryptedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(cryptedPassword);
    }
    
    
    

 
    
    

}
