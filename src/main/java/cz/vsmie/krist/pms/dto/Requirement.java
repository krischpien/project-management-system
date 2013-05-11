package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Jan Krist
 */
@Entity
public class Requirement implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    @Column(name="requirement_name")
    private String name;
    private String content;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="date_create")
    private Date dateCreate;
    
    //     .::RELACE::. 
    
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    
    @OneToMany(mappedBy="requirement")
    @ForeignKey(name="fk_requirement_comment", inverseName="fk_comment_requirement")
    private Set<Comment> comments = new HashSet<Comment>();
    
    @ManyToOne
    @JoinColumn(name="phase_id")
    @ForeignKey(name="fk_requirement_phase")
    private Phase phase;
    
    @OneToOne
    @JoinColumn(name="user_id")
    @ForeignKey(name="fk_requirement_user")
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date createdDate) {
        this.dateCreate = createdDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        User checkedUser = (User) obj;
        return this.getId().equals(checkedUser.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
        return result;
    }
    
    @Override
    public String toString(){
        return "Požadavek#"+id;
    }


  


}
