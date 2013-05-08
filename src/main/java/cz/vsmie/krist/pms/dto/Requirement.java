package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    //     .::RELACE::. 
    
    @OneToMany
    @JoinTable(name="requirement_comments", 
            joinColumns=@JoinColumn(name="requirement_id"),
            inverseJoinColumns=@JoinColumn(name="comment_id"))
    @ForeignKey(name="fk_requirement_comment", inverseName="fk_comment_requirement")
    private Collection<Comment> comments = new ArrayList<Comment>();
    
    @ManyToOne
    @JoinColumn(name="phase_id")
    @ForeignKey(name="fk_requirement_phase")
    private Phase phase;

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

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }


}
