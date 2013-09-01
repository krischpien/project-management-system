package cz.vsmie.krist.pms.service;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/pms-application-test.xml", "classpath:test/pms-servlet-test.xml", "classpath:test/pms-security-test.xml"})
public class PmsMailServiceTest {

    @Autowired
    @Qualifier("mailService")
    ActiveService mailService;
    
    @Test
    public void activeTest(){
        mailService.setActive(true);
        assertTrue(mailService.isActive());
    }
    
    @Test
    public void deactivateTest(){
        mailService.setActive(false);
        assertFalse(mailService.isActive());
    }
    
    
}
