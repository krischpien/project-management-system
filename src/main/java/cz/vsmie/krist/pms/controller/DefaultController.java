package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jan Krist
 */
@Controller
public class DefaultController {
    
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    /**
     * 
     * @return view name
     */
    @RequestMapping("/loginout")
    public String showLoginPage(){
        return "loginout";
    }
    
    @RequestMapping("/")
    public String showHomePage(Model model, Principal principal){
        model.addAttribute("events", eventService.getEventsForUser(principal.getName()));
        model.addAttribute("userRoles", userService.getUserByName(principal.getName()).getRoles());
        return "home";
    }
    
    @RequestMapping("/info")
    public String showInfo(Model model ){
        return "info";
    }
    
}
