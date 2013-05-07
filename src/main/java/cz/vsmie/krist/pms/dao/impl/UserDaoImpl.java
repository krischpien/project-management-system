package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.User;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    public User getById(Long id) {
        Session session = this.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.idEq(id)).setFetchMode("roles", FetchMode.JOIN).uniqueResult();
    }
    
    public User getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }
    
    public User getByEmail(String email){
        Criteria criteria = this.getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    }
    
    public Collection<User> getAll() {
        Collection<User> users = (Collection<User>) this.getCurrentSession().createCriteria(User.class).list();
        for(User u : users){
            logger.info("Getting user: " + u.getName());
        }
        return users;
    }

    public void save(User user){
        Session session = getCurrentSession();
        this.encodePassword(user);
        session.save(user);
    }
    
    public void update(User user){
        this.getCurrentSession().clear();
        this.encodePassword(user);
        this.getCurrentSession().update(user);
    }
    
    public void delete(User user){
        this.getCurrentSession().delete(user);
    }

    private void encodePassword(User user){
        String plainPassword = user.getPassword();
        String cryptedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(cryptedPassword);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
