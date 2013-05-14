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
    
    @Override
    public User getById(Long id) {
        Session session = this.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.idEq(id)).setFetchMode("roles", FetchMode.JOIN).uniqueResult();
    }
    
    @Override
    public User getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }
    
    @Override
    public User getByEmail(String email){
        Criteria criteria = this.getCurrentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    }
    
    @Override
    public Collection<User> getAll() {
        Collection<User> users = (Collection<User>) this.getCurrentSession().createCriteria(User.class).list();
        return users;
    }

    @Override
    public void save(User user){
        Session session = getCurrentSession();
        session.save(user);
    }
    
    @Override
    public void update(User user){
        this.getCurrentSession().clear();
        this.getCurrentSession().update(user);
    }
    
    @Override
    public void delete(User user){
        this.getCurrentSession().delete(user);
    }

    
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
