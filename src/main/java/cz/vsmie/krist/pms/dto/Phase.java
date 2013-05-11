package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jan Krist
 */
@Entity
public class Phase implements Serializable {
    
    public static final Long PHASE_NEW = 1L;
    public static final Long PHASE_APPROVED = 2L;
    public static final Long PHASE_APPRAISED = 3L;
    public static final Long PHASE_REALISATION = 4L;
    public static final Long PHASE_TESTING = 5L;
    public static final Long PHASE_DEPLOYED = 6L;
    public static final Long PHASE_COMPLAINT = 7L;
    
    @Id
    private Long id;
    @Column(name="phase_name")
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    

}
