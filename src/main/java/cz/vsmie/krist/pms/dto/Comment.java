package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Jan Krist
 */

@Entity
public class Comment implements Serializable {
    
    
    @Id @GeneratedValue
    private Long id;
    private String subject;
    @Column(columnDefinition="TEXT")
    private String content;
    
    @Column(name="date_created") 
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    //     .::RELACE::. 
    @ManyToOne
    @JoinColumn(name="user_id")
    private User author;
    

    private Project project;


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }
    
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        Comment checkedComment = (Comment) obj;
        return this.getId().equals(checkedComment.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
        return result;
    }

   

}
