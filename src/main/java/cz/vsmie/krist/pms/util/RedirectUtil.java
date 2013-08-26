package cz.vsmie.krist.pms.util;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author Jan Krist
 */
public class RedirectUtil {
    
    private static final String ADMIN_URL = "/admin/index";
    private static final String CUSTOMER_URL = "/";
    private static final String NOT_AUTHORIZED_URL = "/loginout?notAuthorized";
    
    private static Logger logger = LoggerFactory.getLogger(RedirectUtil.class);
    /**
     * Z objektu <code>Authentication</code> získá veškeré role,
     * na základě kterých se rozhodne o relativní url, na kterou
     * má přihlášeného uživatele přesměrovat.
     * <p>Například v případě, že uživatel bude v roli <code>'ROLE_CUSTOMER'</code>, 
     * vrátí metoda hodnotu <code>'/'</code></p>
     * 
     * @param request
     * @return redirectUrl relativní url adresa
     */
    public static String resolveRedirect(Authentication authentication){
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String redirectUrl="";
        if(authorities.contains("ROLE_ADMIN")){
            logger.debug("User is admin.");
            redirectUrl = ADMIN_URL;
        }
        else if(authorities.contains("ROLE_CUSTOMER") || authorities.contains("ROLE_PROVIDER")){
            logger.debug("User in role customer/provider.");
            redirectUrl = CUSTOMER_URL;
        }
        else{
            logger.debug("User is not authorized.");
            redirectUrl = NOT_AUTHORIZED_URL;
        }
        return redirectUrl;
    }
}
