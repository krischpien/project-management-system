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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Jan Krist
 */

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true)
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
    private boolean deleted = false;
    private boolean advancesPaid = false;
    
    @Column(name="date_create") @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Column(name="date_deadline") @Temporal(TemporalType.DATE)
    private Date dateDeadline;
    @Column(name="date_production") @Temporal(TemporalType.DATE)
    private Date dateProduction;
    @Column(name="date_delivery") @Temporal(TemporalType.DATE)
    private Date dateDelivery;
    //     .::RELACE::. 
    @ManyToMany
    @JoinTable(name="project_permissions", 
            joinColumns=@JoinColumn(name="user_id"), 
            inverseJoinColumns=@JoinColumn(name="project_id"))
    @ForeignKey(name="fk_user_project", inverseName="fk_project_user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<User> authorizedUsers = new HashSet<User>();

    @OneToMany(mappedBy="project", orphanRemoval=true)
    @OrderBy("dateCreate desc")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> comments = new HashSet<Comment>();
    
    @OneToMany(mappedBy="project", orphanRemoval=true)
    @OrderBy("dateCreate desc")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
    private Set<Requirement> requirements = new HashSet<Requirement>();
    
    @ManyToOne
    @JoinColumn(name="phase_id")
    @ForeignKey(name="fk_project_phase")
    @Fetch(FetchMode.JOIN)
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date startDate) {
        this.dateCreate = startDate;
    }

    public Date getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(Date dateDeadline) {
        this.dateDeadline = dateDeadline;
    }

    public Date getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(Date productionDate) {
        this.dateProduction = productionDate;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date deliveryDate) {
        this.dateDelivery = deliveryDate;
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
    public boolean isAdvancesPaid() {
        return advancesPaid;
    }

    public void setAdvancesPaid(boolean advancesPaid) {
        this.advancesPaid = advancesPaid;
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
    
    @Override
    public String toString(){
        return "Projekt#"+id+": "+name;
    }

    

   
    
}
