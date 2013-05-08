package cz.vsmie.krist.pms.converter;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Jan Krist
 */
public class ProjectConverter implements Converter<String, Project> {
    
    @Autowired
    ProjectService projectService;
    

    public Project convert(String pid) {
        return projectService.getProjectById(Long.parseLong(pid));
    }

}
