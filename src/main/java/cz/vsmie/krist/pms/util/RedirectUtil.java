package cz.vsmie.krist.pms.util;

import java.security.Principal;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author Jan Krist
 */
public class RedirectUtil {
    
    private static final String ADMIN_URL = "/admin";
    private static final String CUSTOMER_URL = "/customer";
    private static final String NOT_AUTHORIZED_URL = "/loginout?notAuthorized";
    
    private static Logger logger = LoggerFactory.getLogger(RedirectUtil.class);
    /**
     * Z objektu <code>Authentication</code> získá veškeré role,
     * na základě kterých se rozhodne o relativní url, na kterou
     * má přihlášeného uživatele přesměrovat.
     * <p>Například v případě, že uživatel bude v roli <code>'ROLE_CUSTOMER'</code>, 
     * vrátí metoda hodnotu <code>'/customer'</code></p>
     * 
     * @param request
     * @return redirectUrl relativní url adresa
     */
    public static String resolveRedirect(Authentication authentication){
        logger.debug("start");
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String redirectUrl="";
        if(authorities.contains("ROLE_ADMIN")){
            logger.debug("admin role");
            redirectUrl = ADMIN_URL;
        }
        else if(authorities.contains("ROLE_CUSTOMER")){
            logger.debug("customer role");
            redirectUrl = CUSTOMER_URL;
        }
        else{
            redirectUrl = NOT_AUTHORIZED_URL;
        }
        return redirectUrl;
    }
}
