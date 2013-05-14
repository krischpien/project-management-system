package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.exception.NotFoundException;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.ProjectService;
import cz.vsmie.krist.pms.service.UserService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class ProjectEditController {
    
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    
    Logger logger = LoggerFactory.getLogger(ProjectEditController.class);
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    @RequestMapping({"", "/"})
    public String showProjectIndex(){
        return "projectIndex";
    }
    
    @RequestMapping("/edit/newProject.do")
    public String showProjectForm(@ModelAttribute Project project,Model model){
        populateUsers(model);
        return "projectForm";
    }
    
    @RequestMapping("/edit/edit.do")
    public String editProject(Model model, @RequestParam("pid") Long pid){
        populateUsers(model);
        model.addAttribute("project", projectService.getProjectById(pid));
        return "projectForm";
    }
    
    @RequestMapping(value="/edit/edit.do", method= RequestMethod.POST)
    public String updateProject(@ModelAttribute Project project, Model model, Principal principal){
        projectService.updateProject(project, userService.getUserByName(principal.getName()));
        return "redirect:/project/details/"+project.getId()+"-"+project.getName();
    }
    
    @RequestMapping(value="/edit/newProject.do",method= RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project,Model model){
        projectService.saveProject(project);
        return "redirect:/project/details/"+project.getId()+"-"+project.getName();
    }
    
    
    @RequestMapping(value="/edit/deleteProject.do",method= RequestMethod.POST)
    public String deleteProject(@RequestParam("deletedProjectId") Project project,Model model){
        logger.debug("Odstranovani projektu " + project.getName());
        projectService.deleteProject(project);
        return "redirect:/project/list";
    }
    
    
    @RequestMapping("/details/{pid}-{name}")
    public String showProjectDetails(@ModelAttribute Comment comment, Model model, 
    @PathVariable Long pid, Principal principal, HttpServletRequest request){
        Project project = projectService.getProjectById(pid);
        if(project == null){
            throw new NotFoundException();
        }
        if(!projectService.checkUserPermissionToProject(principal.getName(), project) && !request.isUserInRole("ROLE_ADMIN")){
            return "unauthorizedUser";
        }
        
        model.addAttribute(project);
        return "projectDetails";
    }
    
    @RequestMapping(value="/edit/addComment.do", method= RequestMethod.POST)
    public String saveComment(@ModelAttribute @Valid Comment comment, BindingResult result, @RequestParam("projectId") Long projectId,  HttpServletRequest request, Model model, Principal principal){
        logger.info("Uzivatel " + principal.getName() + " pridava novy komentar");
        if(result.hasErrors()){
            logger.info("Komentar s chybama!");
            model.addAttribute("project", projectService.getProjectById(projectId));
            return "projectDetails";
        }
        projectService.saveComment(comment, projectId, principal.getName());
        return "redirect:/project/details/"+projectId+"-project";
    }
    
    @RequestMapping("/list")
    public String showProjectList(Model model, HttpServletRequest request){
        if(request.isUserInRole("ROLE_ADMIN")){
            model.addAttribute("projectList", projectService.getAllProjects());
        }
        else{
            model.addAttribute("projectList", projectService.getProjectsOfUser(request.getUserPrincipal().getName()));
        }
        
        return "projectList";
    }
    
    
    private void populateUsers(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
    }

}
