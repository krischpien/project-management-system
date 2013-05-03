package cz.vsmie.krist.pms.controllers;

import cz.vsmie.krist.pms.dto.UserDetails;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.UserService;
import java.util.ArrayList;
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
public class DefaultController {
    
    @Autowired
    UserService userService;
    
//    @Autowired
//    public DefaultController(SessionFactory sessionFactory, UserServiceImpl userService){
//        this.sessionFactory = sessionFactory;
//        this.userService = userService;
//    }
    
  
    @RequestMapping("/")
    public String showHomePage(){
        return "home";
    }
    
    @RequestMapping("/loginout")
    public String showLoginPage(){
        return "loginout";
    }
    
    @RequestMapping("/roleList")
    public String showRoleList(Model model){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(UserRole.class);
//        ArrayList<UserRole> roles = (ArrayList<UserRole>)criteria.list();
//        session.getTransaction().commit();
//        
//        model.addAttribute("roles", roles);
        return "roleList";
    }
    
    @RequestMapping("/user/{action}")
    public String newUserForm(@ModelAttribute("user") @Valid UserDetails user, BindingResult results, @PathVariable String action, Model model){
        if(action.equals("save")){
            if(results.hasErrors()){
                model.addAttribute("message", "Žůza!");
                return "userForm";
            }
            try{
                userService.saveUser(user);
            }
            catch(UserNameNotAvailable ex){
                System.out.println(ex.getMessage());
                return "redirect:/user/new?fail";
            }
            
            model.addAttribute("message", "saved");
            return "redirect:/user/new";
        }
        else{
            return "userForm";
        }
        
    }
    
    

}
