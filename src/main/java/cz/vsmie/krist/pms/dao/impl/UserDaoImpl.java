package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.UserDetails;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    public Collection<UserDetails> getAllUsers() {
        return null;
    }

    public void saveUser(UserDetails user){
        Session session = getSession();
        session.save(user);
    }

    public UserDetails getUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (UserDetails) session.get(UserDetails.class, id);
        
    }

    public UserDetails getUserByName(String name) {
        Criteria criteria = getSession().createCriteria(UserDetails.class);
        return (UserDetails) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }
    
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

}
