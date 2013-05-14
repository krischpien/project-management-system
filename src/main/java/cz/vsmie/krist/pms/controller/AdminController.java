package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.service.LoggingService;
import cz.vsmie.krist.pms.service.PmsActiveService;
import cz.vsmie.krist.pms.service.PmsCronScheduler;
import cz.vsmie.krist.pms.service.PmsMailService;
import cz.vsmie.krist.pms.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jan Krist
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    @Qualifier("pmsMailService")
    PmsMailService pmsMailService;
    
    @Autowired
    LoggingService loggingService;
    
    @Autowired
    @Qualifier("pmsCronSchedulerService")
    PmsCronScheduler pmsCronScheduler;
    
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
    public String showAdminConsole(Model model, @RequestParam(value="logOffset",defaultValue="0") int offset, @RequestParam(value="logLimit",defaultValue="20") int limit){
        model.addAttribute("cronActive", pmsCronScheduler.isActive());
        model.addAttribute("mailActive", pmsMailService.isActive());
        model.addAttribute("loggingEvents", loggingService.getAllLoggingEventsPaginated(offset, limit));
        return "adminIndex";
    }
    
    
    @RequestMapping("/service/{serviceName}/{turn}")
    public String modifyService(@PathVariable String serviceName, @PathVariable String turn){
        PmsActiveService service = null;
        if("cron".equals(serviceName)){
            service = pmsCronScheduler;
        }
        else if("mail".equals(serviceName)){
            service = (PmsActiveService) pmsMailService;
        }
        
        if(service == null){
            return "redirect:/admin/index?serviceNotFound";
        }
        if("off".equals(turn)){
            service.setActive(false);
            return "redirect:/admin/index";
        }
        else{
            service.setActive(true);
            return "redirect:/admin/index";
        }
        
        
    }
    
    

}
