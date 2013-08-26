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
     * Vyhledá projekt dle id.
     * @param id - id hledaného projektu
     * @return
     */
    public Project getProjectById(Long id);
    
    /**
     * Vyhledá všechny projekty v databázi.
     * @return všechny projekty v databázi
     */
    public Collection<Project> getAllProjects();
    
    /**
     * Vyhledá všechny projekty daného uživatele v databázi.
     * @param username - jméno uživatele
     * @return všechny projekty uživatele
     */
    public Collection<Project> getProjectsOfUser(String username);
    
    /**
     * Uloží projekt do databáze.
     * @param project
     */
    public void saveProject(Project project);
    
    /**
     * Update project in database.
     * @param project project to save
     * @param next true - project goes to next phase, false - project returns to previous phase
     */
    public void updateProject(Project project, boolean next, User updater);
    
    /**
     * Odstraní pprojekt z databáze.
     * @param project
     */
    public void deleteProject(Project project);
    
    
    /**
     * Odstraní projekt, dle jeho id
     * @param pid id projektu
     */
    public void deleteProjectById(Long pid);
    
        
    /**
     * Uloží nový komentář do databáze.
     * @param comment nový komentář k uložení
     * @param projectId id projektu, ke kterému se komentář vztahuje
     * @param authorName jméno autora komentáře
     */
    public void saveComment(Comment comment, Long projectId, String authorName);
    
    public Collection<Phase> getAllPhases();
    public Phase getPhaseById(Long phaseId);
    
    public Collection<Project> getProjectsWithUnpaidAdvances();
        
    public boolean checkUserPermissionToProject(String username, Project project);
    
    

}
