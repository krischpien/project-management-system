package cz.vsmie.krist.pms.interceptor;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.EventService;
import cz.vsmie.krist.pms.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jan Krist
 */
public class NoticeInterceptor implements HandlerInterceptor{
    
    @Autowired
    UserService userService;
    
    @Autowired
    EventService eventService;
    
    Logger logger = LoggerFactory.getLogger(NoticeInterceptor.class);
    

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
//        logger.debug("NoticeInterceptor intercepting request");
        Principal principal = request.getUserPrincipal();
        if(principal !=null){
            Map countMap = eventService.getEventsCountForUser(principal.getName());
            if(countMap !=null){
                mav.addObject("commentCount", countMap.get("commentCount"));
                mav.addObject("projectCount", countMap.get("projectCount"));
                mav.addObject("requirementCount", countMap.get("requirementCount"));
            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn) throws Exception {
        
    }

}
