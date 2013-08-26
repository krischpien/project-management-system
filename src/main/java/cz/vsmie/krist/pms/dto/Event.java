package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Jan Krist
 */
@Entity
public class Event implements Serializable {

    public static final int PROJECT_UPDATE = 1;
    public static final int NEW_COMMENT = 2;
    public static final int NEW_REQUIREMENT = 3;
    
    
    @Id @GeneratedValue
    private Long id;
    private String description;
    private String link;
    private int type;
    private boolean viewed = false;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateEvent;
    
    @ManyToMany(mappedBy="events")
    private Set<User> listeners = new HashSet<User>();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public Set<User> getListeners() {
        return listeners;
    }

    public void setListeners(Set<User> listeners) {
        this.listeners = listeners;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        Event checkedEvent = (Event) obj;
        return this.getId().equals(checkedEvent.getId());
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
        return "Event#"+id;
    }

    
    

}
