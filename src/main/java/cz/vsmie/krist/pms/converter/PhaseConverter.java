package cz.vsmie.krist.pms.converter;

import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Jan Krist
 */
public class PhaseConverter implements Converter<String, Phase> {
    
    @Autowired
    ProjectService projectService;
    

    @Override
    public Phase convert(String pid) {
        return projectService.getPhaseById(Long.parseLong(pid));
    }

}
