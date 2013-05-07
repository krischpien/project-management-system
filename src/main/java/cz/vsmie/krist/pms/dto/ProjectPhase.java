package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jan Krist
 */
@Entity
@Table(name="project_phase")
public class ProjectPhase implements Serializable {
    
    public static final Long PHASE_NEW = 1L;
    public static final Long PHASE_CREATED = 2L;
    public static final Long PHASE_APPROVED = 3L;
    public static final Long PHASE_APPRAISED = 4L;
    public static final Long PHASE_REALISATION = 5L;
    public static final Long PHASE_TESTING = 6L;
    public static final Long PHASE_DEPLOYED = 7L;
    public static final Long PHASE_COMPLAINT = 8L;
    
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
    

}
