package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.ProjectHistory;
import cz.vsmie.krist.pms.exception.NotFoundException;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.ProjectService;
import cz.vsmie.krist.pms.service.UserService;
import cz.vsmie.krist.pms.util.ProjectFormUtil;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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
    @Autowired
    ProjectFormUtil projectFormUtil;
    @Autowired
    @Qualifier("commentValidator")
    Validator commentValidator;
    @Autowired
    @Qualifier("projectValidator")
    Validator projectValidator;
    
    Logger logger = LoggerFactory.getLogger(ProjectEditController.class);
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
//    @RequestMapping({"", "/"})
    public String showProjectIndex(){
        return "projectIndex";
    }
    
    @RequestMapping("/edit/newProject.do")
    public String showProjectForm(@ModelAttribute Project project,Model model){
        populateUsers(model);
        return "projectForm"; //projectForm //projectFormFlow
    }
    
    @RequestMapping("/edit/edit.do")
    public String editProject(Model model, @RequestParam("pid") Long pid, @ModelAttribute("newComment") Comment comment){
        populateUsers(model);
        model.addAttribute("allPhases", projectService.getAllPhases());
        Project project = projectService.getProjectById(pid);
        if(project == null){
            throw new NotFoundException();
        }
        model.addAttribute("project", project);
        return "projectFormFlow"; //projectForm //projectFormFlow
    }
    
    @RequestMapping(value="/edit/edit.do", method= RequestMethod.POST)
    public String updateProject(@ModelAttribute Project project, BindingResult result, Model model, Principal principal, @RequestParam(value="formAction") String formAction){
        projectValidator.validate(project, result);
        if(result.hasErrors()){
            logger.debug("Project with errors!");
            populatePhases(model);
            populateUsers(model);
            model.addAttribute("newComment", new Comment());

            return "projectFormFlow";
        }
        projectFormUtil.resolveProjectFormAction(project, userService.getUserByName(principal.getName()), formAction);
//        projectService.updateProject(project, true, userService.getUserByName(principal.getName()));
        return "redirect:/project/edit/edit.do?pid="+project.getId();
    }
    
    @RequestMapping(value="/edit/newProject.do",method= RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project, BindingResult result, Model model){
        if(result.hasErrors()){
            logger.debug("Project with errors!");
            populateAll(model);

            return "projectFormFlow";
        }
        projectService.saveProject(project);
        return "redirect:/project/edit/edit.do?pid="+project.getId();
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
        return "projectFormFlow";
    }
    
    @RequestMapping("details/{pid}/history")
    public String showProjectHistory(Model model, @PathVariable Long pid){
        Project project = projectService.getProjectById(pid);
        Collection<ProjectHistory> history = project.getProjectHistory();
        model.addAttribute("projectHistory", history);
        return "projectHistory";
        
    }
    
    @RequestMapping(value="/edit/addComment.do", method= RequestMethod.POST)
    public String saveComment(@ModelAttribute("newComment") Comment comment, BindingResult result, @RequestParam("projectId") Long projectId,  HttpServletRequest request, Model model, Principal principal){
        logger.info("User " + principal.getName() + " is submitting new comment");
        commentValidator.validate(comment, result);
        if(result.hasErrors()){
            logger.debug("Comment with errors!");
            populatePhases(model);
            populateUsers(model);
            model.addAttribute("project", projectService.getProjectById(projectId));
            return "projectFormFlow";
        }
        projectService.saveComment(comment, projectId, principal.getName());
        return "redirect:/project/edit/edit.do?pid="+projectId;
    }
    
    @RequestMapping({"", "/", "/list"})
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
    
    private void populatePhases(Model model){
        model.addAttribute("allPhases", projectService.getAllPhases());
    }
    
    private void populateComment(Model model){
        model.addAttribute("newComment", new Comment());
    }
    
    private void populateAll(Model model){
        populatePhases(model);
        populateUsers(model);
        populateComment(model);
    }

}
