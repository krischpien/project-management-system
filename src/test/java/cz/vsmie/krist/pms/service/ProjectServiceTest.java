package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Project;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/pms-application-test.xml", "classpath:test/pms-servlet-test.xml", "classpath:test/pms-security-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ProjectServiceTest {

    @Autowired
    ProjectService projectService;
    Project testProject = null;
    
    
    @Before
    public void createProjectTest(){
        Project project = new Project();
        project.setName("TestProject");
        project.setContent("TestProject content");
        projectService.saveProject(project);
    }
    
    
    @Test
    public void getProjectTest(){
        for(Project pj : projectService.getAllProjects()){
            if(pj.getName().equals("TestProject")){
                testProject = pj;
                break;
            }
        }
        assertNotNull(testProject);
        assertEquals("Project content test", "TestProject content", testProject.getContent());
    }
    
    @After
    @Transactional
    public void deleteProjectTest(){
        Long pid = testProject.getId();
        System.out.println("proj id: " + pid );
        projectService.deleteProjectById(pid);
        System.out.println("deleting");
        
        testProject = null;
        testProject = projectService.getProjectById(pid);
        assertNull(testProject);
    }
}
