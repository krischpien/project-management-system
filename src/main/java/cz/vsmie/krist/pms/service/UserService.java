package cz.vsmie.krist.pms.service;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.dto.UserRole;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface UserService {
    
    /**
     * Vyhledá všechny uživatele v databázi
     * @return všechny uživatele v databázi
     */
    public Collection<User> getAllUsers();
     /**
     * Vyhledá uživatele podle <code>id</code> v databázi.
     * @param id id uživatele
     * @return uživatel
     */
    public User getUserById(Long id);
    
    /**
     * Vyhledá uživatele podle jména v databázi.
     * @param name jméno uživatele
     * @return uživatel
     */
    public User getUserByName(String name);
    
    /**
     * Vyhledá všechny role v databázi
     * @param assignable <li><code>true</code>: vrátí základní role (Zákazník, Dodavatel)</li><li><code>false</code>: vrátí ostatní</li>
     * @return
     */
    public Collection<UserRole> getAllRoles(boolean assignable);
     
    /**
     * Uloží uživatele do databáze.
     * @param user - uživatel ukládaný do databáze
     * @throws UserNameNotAvailable - byl nalezen uživatel se stejným jménem
     * @throws UserEmailNotAvailable - byl nalezen uživatel se stejnou e-mailovou adresou
     */
    public void saveUser(User user) throws UserNameNotAvailable, UserEmailNotAvailable;
    
    /**
     * Aktualizuje uživatele v databázi. V případě {@code encodePassword} == <code>true </code> 
     * zašifruje uživatelské heslo.
     * @param user uživatel
     * @param encodePassword <code>true</code>: zašifrovat heslo
     * @throws UserNameNotAvailable uživatelské jméno je již v databázi
     * @throws UserEmailNotAvailable uživatelská adresa je již v databázi
     */
    public void updateUser(User user, boolean encodePassword) throws UserNameNotAvailable, UserEmailNotAvailable;
    
    /**
     * Aktualizuje uživatelské údaje o posledním připojení.
     * @param username jméno uživatele
     * @param hostIp ip adresa zařízení, ze kterého se uživatel přihlašuje (nebo poslední proxy).
     */
    public void updateLastLogin(String username, String hostIp);
    
    /**
     * Odstraní uživatele z databáze.
     * @param user
     */
    public void deleteUser(User user);
    public UserRole getRoleById(Long id);
    

}
