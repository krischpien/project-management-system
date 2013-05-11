package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.User;

/**
 *
 * @author Jan Krist
 */
public interface PmsMailService extends PmsActiveService{
    
    public void sendCreateUserNotice(User user);

}
