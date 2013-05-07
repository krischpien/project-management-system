package cz.vsmie.krist.pms.exception;

/**
 *
 * @author Jan Krist
 */
public class UserEmailNotAvailable extends UserException{

    public UserEmailNotAvailable(String email) {
        super("Emailová adresa <b>'" + email + "'</b> je již v databázi");
    }
    
    

}
