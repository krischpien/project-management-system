package cz.vsmie.krist.pms.validator;

import cz.vsmie.krist.pms.dto.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Jan Krist
 */
@Component("commentValidator")
public class CommentValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Comment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Comment comment = (Comment) target;
        ValidationUtils.rejectIfEmpty(errors, "subject", "comment.subject.empty");
        ValidationUtils.rejectIfEmpty(errors, "content", "comment.content.empty");
        
    }

}
