package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
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
     * @param uid - id uživatele
     * @return všechny projekty uživatele
     */
    public Collection<Project> getProjectsOfUser(Long uid);
    
    /**
     * Uloží projekt do databáze.
     * @param project
     */
    public void saveProject(Project project);
    
    /**
     * Aktualizuje projekt v databázi.
     * @param project
     */
    public void updateProject(Project project);
    
    /**
     * Odstraní pprojekt z databáze.
     * @param project
     */
    public void deleteProject(Project project);
    
    /**
     * Uloží nový komentář do databáze.
     * @param comment nový komentář k uložení
     * @param projectId id projektu, ke kterému se komentář vztahuje
     * @param authorName jméno autora komentáře
     */
    public void saveComment(Comment comment, Long projectId, String authorName);
    
    /**
     * Uloží nový komentář do databáze.
     * @param requirement nový požadavek k uložení
     * @param projectId id projektu, ke kterému se komentář vztahuje
     * @param authorName jméno autora komentáře
     */
    public void saveRequirement(Requirement requirement, Long projectId, String authorName);

}
