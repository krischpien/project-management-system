package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jan Krist
 */
@Entity
@Table(name="project_history")
public class ProjectHistory implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private User author;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="date_change")
    private Date dateChange;
    
    private String note;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        ProjectHistory checkedHistory = (ProjectHistory) obj;
        return this.getId().equals(checkedHistory.getId());
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
        return "ProjectHistory#"+id;
    }

    

}
