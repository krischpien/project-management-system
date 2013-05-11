package cz.vsmie.krist.pms.service.impl;

import cz.vsmie.krist.pms.component.impl.ScheduledAsyncWorker;
import cz.vsmie.krist.pms.service.PmsActiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */

@Service
public class PmsCronSchedulerImpl extends PmsActiveService{

    @Autowired
    ScheduledAsyncWorker asyncWorker;
    
    Logger logger = LoggerFactory.getLogger(PmsCronSchedulerImpl.class);
    
    @Scheduled(cron="0 * * * * *")
    public void doSomething() {
        if(isActive()){
            logger.info("DO PLANNED THINGS");
            asyncWorker.doSomethingAsynchronously();
        }
        else{
            logger.debug("Scheduler's not active");
        }
        
    }


}
