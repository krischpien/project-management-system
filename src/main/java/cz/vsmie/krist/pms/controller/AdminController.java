package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.exception.UserException;
import cz.vsmie.krist.pms.service.PmsActiveService;
import cz.vsmie.krist.pms.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jan Krist
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PmsActiveService pmsCronScheduler;
    
    @Autowired
    UserService userService;
    
    @RequestMapping("")
    public String showHomePage(){
        return "home";
    }
    
    @RequestMapping("/")
    public String showAdminPage(){
        return "home";
    }
    
    @RequestMapping("/index")
    public String showAdminConsole(Model model){
        model.addAttribute("cronActive", pmsCronScheduler.isActive());
        return "adminIndex";
    }
    @RequestMapping("/cron/{turn}")
    public String modifyCron(@PathVariable String turn){
        if("off".equals(turn)){
            pmsCronScheduler.setActive(false);
        }
        else{
            pmsCronScheduler.setActive(true);
        }
        return "redirect:/admin/index";
    }
    
    

}
