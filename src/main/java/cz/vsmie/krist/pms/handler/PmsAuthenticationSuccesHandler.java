package cz.vsmie.krist.pms.handler;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.UserService;
import cz.vsmie.krist.pms.util.RedirectUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 *
 * @author Jan Krist
 */

public class PmsAuthenticationSuccesHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Autowired
    UserService userService;
    
    Logger log = LoggerFactory.getLogger(PmsAuthenticationSuccesHandler.class);
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("autentifikační handler běží");
        
        User user = userService.getUserByName(authentication.getName());
        request.getSession().setAttribute("lastLogin", user.getLastLogin() == null? new Date() :user.getLastLogin());
        request.getSession().setAttribute("lastIp", user.getLastIp() == null? request.getRemoteAddr() : user.getLastIp());
        userService.updateLastLogin(authentication.getName(), request.getRemoteAddr());
        
        setDefaultTargetUrl(RedirectUtil.resolveRedirect(authentication));
        super.onAuthenticationSuccess(request, response, authentication);
    }
    

}
