package cz.vsmie.krist.pms.component;

import cz.vsmie.krist.pms.component.impl.ScheduledAsyncWorker;
import cz.vsmie.krist.pms.service.UserService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan Krist
 */
@Service("scheduledAsyncWorker")
//@EnableAsync
public class ScheduledAsyncWorkerImpl implements ScheduledAsyncWorker{
    
    @Autowired
    UserService userService;
    
    Logger logger = LoggerFactory.getLogger(ScheduledAsyncWorkerImpl.class);

    @Async
    public void doSomethingAsynchronously()  {
        logger.info("Cron fired action at " + new Date());
    }

}
