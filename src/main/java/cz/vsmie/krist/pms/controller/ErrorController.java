package cz.vsmie.krist.pms.controller;

import org.springframework.stereotype.Controller;
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
    public String showError(@PathVariable String errorCode){
        return errorCode;
    }
}
