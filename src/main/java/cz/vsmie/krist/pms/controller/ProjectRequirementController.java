package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.Requirement;
import cz.vsmie.krist.pms.service.ProjectService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jan Krist
 */
@Controller
@RequestMapping("/project")
public class ProjectRequirementController {

    @Autowired
    ProjectService projectService;
    
    @RequestMapping("/{projectId}-{projectName}/requirement/newRequirement.do")
    public String showRequirementForm(@ModelAttribute Requirement requirement, Model model, @PathVariable Long projectId){
        model.addAttribute(projectId);
        return "requirementForm";
    }
    
    @RequestMapping(value="/{projectId}-{projectName}/requirement/newRequirement.do", method= RequestMethod.POST)
    public String saveRequirement(@ModelAttribute Requirement requirement, @RequestParam("projectId") Long projectId, Principal principal){
        projectService.saveRequirement(requirement, projectId, principal.getName());
        return "redirect:/project/details/"+projectId+"-project"; // vrátí na detail projektu (tiles-view:"projectDetails")
    }
}
