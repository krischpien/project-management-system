package cz.vsmie.krist.pms.validator;

import cz.vsmie.krist.pms.dto.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Jan Krist
 */
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        
        if(user.getName().length() < 3 || user.getName().length() >50){
            errors.rejectValue("name", "user.name.size");
        }
        Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher emailMatch = emailPattern.matcher(user.getEmail());
        if(!emailMatch.matches()){
            errors.rejectValue("email", "user.email.pattern");
        }
        
        
    }

    
    
}
