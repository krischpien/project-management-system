package cz.vsmie.krist.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jan Krist
 */
@Controller
@RequestMapping("/httperror")
public class ErrorController {

    @RequestMapping("/{errorCode}")
    public String showError(Model model, @PathVariable String errorCode){
//        model.addAttribute("message", "Stránka nenalezena");
        return errorCode;
    }
}
