package cz.vsmie.krist.pms.validator;

import cz.vsmie.krist.pms.dto.Phase;
import cz.vsmie.krist.pms.dto.Project;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Jan Krist
 */
@Component("projectValidator")
public class ProjectValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "project.name.empty");
        
        if(project.getPhase() != null){
            if(project.getPhase().getId() == Phase.PHASE_NEW){
               ValidationUtils.rejectIfEmpty(errors, "content", "project.content.empty"); 
               ValidationUtils.rejectIfEmpty(errors, "dateDeadline", "project.dateDeadline.empty");
            }
            if(project.getPhase().getId() == Phase.PHASE_PLACED){
                ValidationUtils.rejectIfEmpty(errors, "budget", "project.budget.empty"); 
            }
        }
        else if(project.getPhase() == null){
            ValidationUtils.rejectIfEmpty(errors, "name", "project.name.empty");
        }
        
    }

}
