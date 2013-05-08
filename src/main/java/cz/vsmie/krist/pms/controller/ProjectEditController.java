package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.Comment;
import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.service.ProjectService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "projectForm";
    }
    
    @RequestMapping("/edit/edit.do")
    public String editProject(@ModelAttribute Project project, Model model, @RequestParam("pid") Long pid){
        model.addAttribute("project", projectService.getProjectById(pid));
        return "projectForm";
    }
    
    @RequestMapping(value="/edit/edit.do", method= RequestMethod.POST)
    public String updateProject(@ModelAttribute Project project, Model model){
        Project persistedProject = projectService.getProjectById(project.getId());
        project.setComments(persistedProject.getComments());
        projectService.updateProject(project);
        return "redirect:/project/details/"+project.getId()+"-"+project.getName();
    }
    
    @RequestMapping(value="/edit/newProject.do",method= RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project,Model model){
        projectService.saveProject(project);
        return "redirect:/project/details/"+project.getId()+"-"+project.getName();
    }
    
    @RequestMapping("/details/{pid}-{name}")
    public String showProjectDetails(@ModelAttribute Comment comment, Model model, @PathVariable Long pid, @PathVariable String name){
        Project project = projectService.getProjectById(pid);
        model.addAttribute(project);
        return "projectDetails";
    }
    
    @RequestMapping(value="/edit/addComment.do", method= RequestMethod.POST)
    public String saveComment(@ModelAttribute Comment comment, @RequestParam("projectId") Project project, HttpServletRequest request){
        logger.info("adding comments");
//        comment.setDate(new Date());
//        project.getComments().add(comment);
//        projectService.updateProject(project);
        projectService.saveComment(comment, project, request.getUserPrincipal());
        return "redirect:/project/details/"+project.getId()+"-"+project.getName();
    }
    
    @RequestMapping("/list")
    public String showProjectList(Model model){
        model.addAttribute("projectList", projectService.getAllProjects());
        return "projectList";
    }

}
