package cz.vsmie.krist.pms.exception;

/**
 *
 * @author Jan Krist
 */
public class UserNameNotAvailable extends UserException {

    public UserNameNotAvailable() {
    }

    public UserNameNotAvailable(String username) {
        super("Uživatel <b>'" + username + "'</b> je již v databázi");
    }
    
    

}
