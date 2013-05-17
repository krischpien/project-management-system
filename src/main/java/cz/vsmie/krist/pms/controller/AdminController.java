package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.service.LoggingService;
import cz.vsmie.krist.pms.service.PmsActiveService;
import cz.vsmie.krist.pms.service.PmsAdvancesAudit;
import cz.vsmie.krist.pms.service.PmsMailService;
import cz.vsmie.krist.pms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jan Krist
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private PmsMailService pmsMailService;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private PmsAdvancesAudit pmsAdvancesAudit; //scheduled
    
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @RequestMapping("")
    public String showHomePage(){
        return "home";
    }
    
    @RequestMapping("/")
    public String showAdminPage(){
        return "home";
    }
    
    @RequestMapping("/index")
    public String showAdminConsole(Model model, @RequestParam(value="logOffset", defaultValue="0") int offset, @RequestParam(value="logLimit", defaultValue="50") int limit){
        model.addAttribute("cronActive", pmsAdvancesAudit.isActive());
        model.addAttribute("mailActive", pmsMailService.isActive());
        int logCount = loggingService.getLoggingEventCount().intValue();
        int pageCount = logCount/limit;
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("loggingEvents", loggingService.getAllLoggingEventsPaginated(offset, limit));
        return "adminIndex";
    }
    
    @RequestMapping(value="/clearLog.do", method= RequestMethod.POST)
    public String clearLoggingEvents(){
        loggingService.deleteAllLoggingEvents();
        return "redirect:/admin/index";
    }
    
    
    @RequestMapping("/service/{serviceName}/{turn}")
    public String modifyService(@PathVariable String serviceName, @PathVariable String turn){
        PmsActiveService service = null;
        if("audit".equals(serviceName)){
            service = pmsAdvancesAudit;
        }
        else if("mail".equals(serviceName)){
            service = (PmsActiveService) pmsMailService;
        }
        
        if(service == null){
            logger.warn("Sluzba " + serviceName + " nebyla nalezena");
            return "redirect:/admin/index?serviceNotFound";
        }
        
        if("off".equals(turn)){
            service.setActive(false);
            logger.info("Sluzba " + serviceName + " byla vypnuta.");
            return "redirect:/admin/index";
        }
        else{
            service.setActive(true);
            logger.info("Sluzba " + serviceName + " byla zapnuta.");
            return "redirect:/admin/index";
        }
        
        
    }
    
    

}
