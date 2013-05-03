package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.UserDetails;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Krist
 */

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserDao userDao;    
    
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
//    public UserService(SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
    
    public Collection<UserDetails> getAllUsers(){
        Collection<UserDetails> users = new ArrayList<UserDetails>();
        return users;
    }
    
    
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void saveUser(UserDetails user) throws UserNameNotAvailable{
        logger.info("Lookin' for " + user.getName());
        if(userDao.getUserByName(user.getName())== null){
            userDao.saveUser(user);
        }
        else{
            logger.info("Found: " + user.getName() + ", " + user.getEmail()+ ", throwing an Exception");
            throw new UserNameNotAvailable(user.getName());
        }
    }

    public UserDetails getUserById(long id) {
        return userDao.getUserById(id);
    }

    public UserDetails getUserByName(String name) {
        return userDao.getUserByName(name);
    }

}
