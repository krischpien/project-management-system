package cz.vsmie.krist.pms.converter;

import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Jan Krist
 */
final class UserRoleConverter implements Converter<String,UserRole>{
    
    @Autowired
    UserService userService;
    
    Logger logger = LoggerFactory.getLogger(UserRoleConverter.class);
    
    public UserRoleConverter(){
        logger.warn("Creating UserRoleConverter");
    }

    public UserRole convert(String id) {
        return (UserRole) userService.getRoleById(Long.parseLong(id));
    }

    

    

}
