package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.Project;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */

public interface ProjectDao extends GenericDao<Project> {
    
    public Collection<Project> getProjectWithUnpaidAdvances();

}
