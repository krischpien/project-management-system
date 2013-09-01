package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/pms-application-test.xml", "classpath:test/pms-servlet-test.xml", "classpath:test/pms-security-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Before
    public void createUser() throws UserNameNotAvailable, UserEmailNotAvailable{
        User user = new User();
        user.setEmail("test@test.tt");
        user.setName("TestUserTest");
        user.setPassword("Noncrypted");
        userService.saveUser(user);
    }
    
    @Test
    public void getUserByNameTest(){
        User user = null;
        user = userService.getUserByName("TestUserTest");
        assertNotNull(user);
    }
    
    @Test
    public void getUserByEmailTest(){
        User user = null;
        user = userService.getUserByEmail("test@test.tt");
        assertNotNull(user);
    }
    
    @Test
    public void testNonCryptedPassword(){
        User user = null;
        user = userService.getUserByEmail("test@test.tt");
        assertNotNull(user);
        assertNotEquals("Noncrypted", user.getPassword());
    }
    
    @Test
    public void testCryptedPassword(){
        User user = userService.getUserByName("TestUserTest");
        String testCrypt = passwordEncoder.encode("Noncrypted");
        String crypted = user.getPassword();
        assertNotEquals(crypted, testCrypt); //must be salted
    }
    
    @After
    @Transactional
    public void removeUser(){
        User user = null;
        user = userService.getUserByEmail("test@test.tt");
        userService.deleteUserById(user.getId());
    }
    
}
