package cz.vsmie.krist.pms.service;

import cz.vsmie.krist.pms.dto.Project;
import org.hibernate.Criteria;
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
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class ProjectServiceTest {

    @Autowired
    ProjectService projectService;
    Project testProject;
    
    
    
    
//    @Before
    public void createProjectTest(){
        Project project = new Project();
        project.setName("TestProject");
        project.setContent("TestProject content");
        projectService.saveProject(project);
    }
    
    
    @Test
    public void deleteProjectTest(){
       
    }
    
//    @Test
    public void getProjectTest(){
        
        for(Project pj : projectService.getAllProjects()){
            if(pj.getName().equals("TestProject")){
                testProject = pj;
                break;
            }
        }
        assertEquals("Project content test", "TestProject content", testProject.getContent());
        projectService.deleteProject(this.testProject);
    }
    

}
