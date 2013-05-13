package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Requirement;

/**
 *
 * @author Jan Krist
 */
public interface RequirementService {

    public Requirement getRequirementById(Long id);
    
    public void saveRequirement(Requirement requirement, Long projectId, String authorName);
    
    
}
