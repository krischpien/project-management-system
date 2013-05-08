package cz.vsmie.krist.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jan Krist
 */
@Controller
public class DefaultController {

    /**
     * 
     * @return view name
     */
    @RequestMapping("/loginout")
    public String showLoginPage(){
        return "loginout";
    }
    
    @RequestMapping("/")
    public String showHomePage(){
        return "home";
    }
    
    @RequestMapping("/info")
    public String showInfo(){
        return "info";
    }
    
}
