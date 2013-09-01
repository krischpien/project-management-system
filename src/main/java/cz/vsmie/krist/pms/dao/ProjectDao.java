package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.Project;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */

public interface ProjectDao extends GenericDao<Project> {
    
    /**
     * Search for project, which has dateDeadline attribute past current date
     * @return project
     */
    public Collection<Project> getProjectWithDeadline();

}
