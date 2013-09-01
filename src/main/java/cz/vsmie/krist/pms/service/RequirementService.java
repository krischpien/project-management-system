package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Requirement;

/**
 *
 * @author Jan Krist
 */
public interface RequirementService {

    /**
     * Search requirement by its id
     * @param id
     * @return requirement
     */
    public Requirement getRequirementById(Long id);
    
    /**
     * Save requirement.
     * @param requirement
     * @param projectId
     * @param authorName 
     */
    public void saveRequirement(Requirement requirement, Long projectId, String authorName);
    
    
}
