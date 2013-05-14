package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.RoleDao;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.Event;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserException;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.EventService;
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
    EventService eventService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
   
    @Override
    public Collection<User> getAllUsers(){
        return userDao.getAll();
    }
    
    @Override
    public void saveUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable{
        logger.debug("Hledam uzivatele " + user.getName());
        if(userDao.getByName(user.getName())!= null){
            logger.info("Uzivatel " + user.getName() + "je jiz v databázi");
            throw new UserNameNotAvailable(user.getName());
        }
        if(userDao.getByEmail(user.getEmail()) != null){
            logger.info("Emailova adresa " + user.getEmail() + "je jiz v databázi");
            throw new UserEmailNotAvailable(user.getEmail());
        }
        else{
            encodePassword(user);
            userDao.save(user);
            mailService.sendCreateUserNotice(user); //async
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getByName(name);
    }
    
    @Override
    public User getUserByEmail(String email){
        return userDao.getByEmail(email);
    }

    @Override
    public Collection<UserRole> getAllRoles(boolean assignable) {
        if(assignable){
            return roleDao.getAssignableRoles();
        }
        return roleDao.getMainRoles();
    }

    @Override
    public UserRole getRoleById(Long id) {
        return roleDao.getById(id);
    }
    
    @Override
    public void deleteUser(User user){
        logger.debug("metoda deleteUser(User user) neimplementována");
    }

    @Override
    public void deleteUserById(Long uid) {
        User user = userDao.getById(uid);
        for(Event event : user.getEvents()){
            event.getListeners().remove(user);
        }
        for(UserRole role : user.getRoles()){
            role.getUsers().remove(user);
        }
        for(Project project :user.getProjects()){
            project.getAuthorizedUsers().remove(user);
        }
        userDao.delete(user);
    }

    @Override
    public void updateUser(User updatedUser, boolean encodePassword) throws UserException {
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
    
    @Override
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
