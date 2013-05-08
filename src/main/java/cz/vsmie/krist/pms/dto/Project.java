package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Jan Krist
 */

@Entity
public class Project implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    
    @Column(name="project_name")
    private String name;
    @Column(columnDefinition="TEXT")
    private String content;
    private String note;
    private double budget;
    private double fee;
    private boolean deleted;
    
    @Column(name="date_start") @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name="date_deadline") @Temporal(TemporalType.DATE)
    private Date dateDeadline;
    @Column(name="date_production") @Temporal(TemporalType.DATE)
    private Date productionDate;
    @Column(name="date_delivery") @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    //     .::RELACE::. 
    @ManyToMany
    @JoinTable(name="project_permissions", 
            joinColumns=@JoinColumn(name="project_id"), 
            inverseJoinColumns=@JoinColumn(name="user_id"))
    @ForeignKey(name="fk_project_user", inverseName="fk_user_project")
    private Set<User> authorizedUsers = new HashSet<User>();

    @OneToMany(orphanRemoval=true)
    @JoinTable(name="project_comments", joinColumns=@JoinColumn(name="project_id"),inverseJoinColumns=@JoinColumn(name="comment_id"))
    @ForeignKey(name="fk_project_comment", inverseName="fk_comment_project")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @OrderBy("createDate desc")
    private Set<Comment> comments = new HashSet<Comment>();
    
    @OneToMany
    @JoinTable(name="project_requirements", joinColumns=@JoinColumn(name="project_id"), inverseJoinColumns=@JoinColumn(name="requirement_id"))
    @ForeignKey(name="fk_project_requirement", inverseName="fk_requirement_project")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @OrderBy("createDate desc")
    private Set<Requirement> requirements = new HashSet<Requirement>();
    
    @ManyToOne
    @JoinColumn(name="phase_id")
    @ForeignKey(name="fk_project_phase")
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(Date dateDeadline) {
        this.dateDeadline = dateDeadline;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<User> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(Set<User> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    
    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        Project checkedProject = (Project) obj;
        return this.getId().equals(checkedProject.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
        return result;
    }

   
    
}
