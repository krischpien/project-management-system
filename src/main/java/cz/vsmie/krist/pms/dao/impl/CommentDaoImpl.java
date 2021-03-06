package cz.vsmie.krist.pms.dao.impl;

import cz.vsmie.krist.pms.dao.CommentDao;
import cz.vsmie.krist.pms.dto.Comment;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Krist
 */
@Repository
public class CommentDaoImpl implements CommentDao{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Comment getById(Long id) {
        return (Comment) getCurrentSession().get(Comment.class, id);
    }

    @Override
    public Collection<Comment> getAll() {
        return (Collection<Comment>) getCurrentSession().createCriteria(Comment.class).list();
    }

    @Override
    public void save(Comment comment) {
        getCurrentSession().save(comment);
    }

    @Override
    public void update(Comment comment) {
        getCurrentSession().update(comment);
    }

    @Override
    public void delete(Comment comment) {
        getCurrentSession().delete(comment);
    }
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
