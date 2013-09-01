package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;

/**
 *
 * @author Jan Krist
 */
public interface PmsMailService{
    
    /**
     * Send mail to new (created) user
     * @param user 
     */
    public void sendCreateUserNotice(User user);
    
    /**
     * Send mail notice about project deadline
     * @param project
     * @param user 
     */
    public void sendDeadlineNotice(Project project, User user);

}
