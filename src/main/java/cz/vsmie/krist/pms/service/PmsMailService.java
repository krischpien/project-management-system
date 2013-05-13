package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Project;
import cz.vsmie.krist.pms.dto.User;

/**
 *
 * @author Jan Krist
 */
public interface PmsMailService extends PmsActiveService{
    
    public void sendCreateUserNotice(User user);
    public void sendUnpaidAdvancesNotice(Project project, User user);

}
