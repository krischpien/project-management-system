package cz.vsmie.krist.pms.converter;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Jan Krist
 */
public class UserConverter implements Converter<String,User>{

    @Autowired
    UserService userService;

    public User convert(String uid) {
        return userService.getUserById(Long.parseLong(uid));
    }


}
