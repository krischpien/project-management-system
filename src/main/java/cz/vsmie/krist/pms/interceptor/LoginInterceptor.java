package cz.vsmie.krist.pms.interceptor;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.UserService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jan Krist
 */
public class LoginInterceptor implements HandlerInterceptor{
    
    @Autowired
    UserService userService;
    
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    

    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        logger.info("**INTERCEPTING** postHandle()");
        HttpSession session = hsr.getSession();
        Object loginDateUpdated = (Object) session.getAttribute("loginDateUpdated");
        if(loginDateUpdated == null){ // pokud neni atribut loginDateUpdate v aktualni session
            String principalName = hsr.getUserPrincipal().getName();
            String remoteAddr = hsr.getRemoteAddr();
            User user = userService.getUserByName(principalName);
            
            // ulozi do session promenne lastIp a lastDate pro informaci o poslednim pristupu uzivatele
            session.setAttribute("lastIp", user.getLastIp()); 
            logger.info("lastIp: "+ user.getLastIp());
            session.setAttribute("lastDate", user.getLastLogin());
            logger.info("lastLogin: "+ user.getLastLogin());
            
            // aktualizaca uzivatele s novymi udaji o aktualnim prihlaseni
            user.setLastLogin(new Date());
            user.setLastIp(remoteAddr);
            userService.updateUser(user);
            session.setAttribute("loginDateUpdated",true);
            logger.info("Přihlášen uživatel: " + principalName + ", z " + remoteAddr);
        }
        else{
            logger.debug("Poslední přihlášení již bylo aktualizováno");
        }
    }

    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        
    }

}
