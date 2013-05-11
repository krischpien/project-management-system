package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.component.impl.ScheduledAsyncWorker;
import cz.vsmie.krist.pms.service.PmsActiveService;
import cz.vsmie.krist.pms.service.PmsCronScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */

@Service("pmsCronSchedulerService")
public class PmsCronSchedulerImpl implements PmsCronScheduler{

    @Autowired
    ScheduledAsyncWorker asyncWorker;
    
    private boolean active = true;
    
    Logger logger = LoggerFactory.getLogger(PmsCronSchedulerImpl.class);
    
    @Scheduled(cron="0 5 * * * *")
    public void doSomething() {
        if(isActive()){
            logger.info("Probíhá naplánovaná úloha");
            asyncWorker.doSomethingAsynchronously();
        }
        else{
            logger.debug("Plánovač není aktivní");
        }
        
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
