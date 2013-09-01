package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface ProjectService {
    
    /**
     * Look for project by its id
     * @param id - id of desired project
     * @return project
     */
    public Project getProjectById(Long id);
    
    /**
     * Search for all projects in database.
     * @return all project in database
     */
    public Collection<Project> getAllProjects();
    
    /**
     * Search for all project in database by user's name.
     * @param username - name of project owner
     * @return all project of specified user.
     */
    public Collection<Project> getProjectsOfUser(String username);
    
    /**
     * Save project to database.
     * @param project
     */
    public void saveProject(Project project);
    
    /**
     * Update project in database.
     * @param project project to save
     * @param note - note to project update
     * 
     */
    public void updateProject(Project project, User updater, String note);
    
    /**
     * Remove project from database
     * @param project
     */
    public void deleteProject(Project project);
    
    
    /**
     * Remove project by its id attribute
     * @param pid id of project
     */
    public void deleteProjectById(Long pid);
    
        
    /**
     * Save new comment to database
     * @param comment comment to save
     * @param projectId id of commented project
     * @param authorName name of comment's author
     */
    public void saveComment(Comment comment, Long projectId, String authorName);
    
    /**
     * Look-up for all possible phases
     * @return phases
     */
    public Collection<Phase> getAllPhases();
    
    /**
     * Look for phase by its id
     * @param phaseId
     * @return phase
     */
    public Phase getPhaseById(Long phaseId);
    
    
    /**
     * This method will find all project with approaching deadline or past deadline
     * @return project
     */
    public Collection<Project> getProjectsWithDeadline();
        
    /**
     * This method will check user permission to project
     * @param username
     * @param project
     * @return true if user is permitted, false otherwise
     */
    public boolean checkUserPermissionToProject(String username, Project project);
    
    

}
