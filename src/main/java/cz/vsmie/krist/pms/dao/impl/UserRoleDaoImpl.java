package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.RoleDao;
import cz.vsmie.krist.pms.dto.UserRole;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */
@Repository
public class UserRoleDaoImpl implements RoleDao{
    
    @Autowired
    SessionFactory sessionFactory;

    public Set<UserRole> getAll() {
        return (Set<UserRole>) sessionFactory.getCurrentSession().createCriteria(UserRole.class).list();
    }
    
    public Set<UserRole> getMainRoles() {
        Criteria criteria = this.getCurrentSession().createCriteria(UserRole.class).add(Restrictions.or(Restrictions.idEq(2L), Restrictions.idEq(3L)));
        sessionFactory.close();
        return (Set<UserRole>) criteria.list();
    }

    public Set<UserRole> getAssignableRoles() {
        Criteria criteria = this.getCurrentSession().createCriteria(UserRole.class).add(Restrictions.gt("id", 3L));
        return (Set<UserRole>) criteria.list();
    }

    public UserRole getById(long id) {
        return (UserRole) this.getCurrentSession().get(UserRole.class, id);
    }
    

    public UserRole getById(Long id) {
        return (UserRole) this.getCurrentSession().get(UserRole.class, id);
    }

    public void save(UserRole role) {
        this.getCurrentSession().save(role);
    }

    public void update(UserRole role) {
        this.getCurrentSession().update(role);
    }

    public void delete(UserRole role) {
        this.getCurrentSession().delete(role);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    

    
}
