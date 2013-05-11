package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.exception.UserException;
import cz.vsmie.krist.pms.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/admin/user")
public class UserEditController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping("/")
    public String showUserIndex(){
        return "userIndex";
    }
    
    @RequestMapping("/edit/edit.do")
    public String editUserForm(Model model, @RequestParam("uid") Long id){
        populateRoles(model);
        model.addAttribute(userService.getUserById(id));
        return "userForm";
    }
    
    @RequestMapping(value="/edit/edit.do", method= RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult results, Model model){
        populateRoles(model);
        if(results.hasErrors()){
            return "userForm";
        }
        try{
            userService.updateUser(user, true);
        }
        catch(UserException ex){
            model.addAttribute("message", ex.getMessage());
            return "userForm";
        }
        return "redirect:/admin/user/details/"+user.getName();
    }
    
    

    @RequestMapping(value="/edit/newUser.do", method= RequestMethod.GET)
    public String showUserForm(@ModelAttribute("user") User user, Model model){
        this.populateRoles(model);
        return "userForm";
    }
    
    @RequestMapping(value="/edit/newUser.do", method= RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult results, Model model){
        this.populateRoles(model);
            if(results.hasErrors()){
                return "userForm";
            }
            try{
                userService.saveUser(user);
            }
            catch(UserException ex){
                model.addAttribute("message", ex.getMessage());
                return "userForm";
            }
            return "redirect:/admin/user/details/"+user.getName();
    }
    
    @RequestMapping(value="/edit/deleteUser.do", method= RequestMethod.POST)
    public String deleteUser(@RequestParam("deletedUserId") Long uid, Model model){
        User user = userService.getUserById(uid);
        if(user == null){
            model.addAttribute("message", "Uživatel s id " + uid +" nebyl nalezen");
            return "userList";
        }
        userService.deleteUser(user);
        return "redirect:/admin/user/list?deleted=ok&name="+user.getName();
    }
    
    @RequestMapping("/details/{name}")
    public String showUserDetails(@PathVariable String name, Model model){
            User user = userService.getUserByName(name);
            model.addAttribute("user", user);
            return "userDetails";
    }
    
    @RequestMapping("/list")
    public String showUserList(Model model){
        model.addAttribute("userList", userService.getAllUsers());
        return "userList";
    }
    
    private void populateRoles(Model model){
        model.addAttribute("assignableRoles", userService.getAllRoles(true));
        model.addAttribute("mainRoles", userService.getAllRoles(false));
    }
}
