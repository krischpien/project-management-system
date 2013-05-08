package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Project;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface ProjectService {
    
    public Project getProjectById(Long id);
    public Collection<Project> getAllProjects();
    public Collection<Project> getProjectsOfUser(Long uid);
    public void saveProject(Project project);
    public void updateProject(Project project);
    public void deleteProject(Project project);

}
