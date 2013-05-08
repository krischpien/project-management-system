package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.Requirement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jan Krist
 */
//@Controller
@RequestMapping("/project")
public class ProjectRequirementController {

    
    @RequestMapping("/{projectId}/requirement/newRequirement.do")
    public String showRequirementForm(@ModelAttribute Requirement requirement){
        return "requirementForm";
    }
    
    @RequestMapping(value="/{projectId}/requirement/newRequirement.do", method= RequestMethod.POST)
    public String saveRequirement(){
        return "requirementForm";
    }
}
