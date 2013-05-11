package cz.vsmie.krist.pms.controller;

import cz.vsmie.krist.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jan Krist
 */

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    
    @Autowired
    UserService userService;

    @RequestMapping(value="/checkUserName/{username}", method= RequestMethod.GET, produces="text/plain; charset=utf-8")
    public @ResponseBody String checkUserNameAvailabilty(@PathVariable String username){
        if(userService.getUserByName(username)==null){
            return "Uživatelské jméno je dostupné";
        }
        else{
            return "Uživatelské jméno <b>" + username + "</b> je již používáno!";
        }
    }
    
    
    @RequestMapping(value="/checkUserEmail/", method= RequestMethod.GET, produces="text/plain; charset=utf-8")
    public @ResponseBody String checkUserEmailAvailabilty(@RequestParam String email){
        if(userService.getUserByEmail(email)==null){
            return "Uživatelský email je dostupný";
        }
        else{
            return "Uživatelský email <b>" + email + "</b> je již používán!";
        }
    }
    
}
