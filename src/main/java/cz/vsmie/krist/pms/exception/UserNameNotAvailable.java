package cz.vsmie.krist.pms.exception;

/**
 *
 * @author Jan Krist
 */
public class UserNameNotAvailable extends Exception {

    public UserNameNotAvailable() {
    }

    public UserNameNotAvailable(String username) {
        super("Uživatel " + username + " je již v databázi");
    }
    
    

}
