package cz.vsmie.krist.pms.component;

import cz.vsmie.krist.pms.component.impl.ScheduledAsyncWorker;
import cz.vsmie.krist.pms.dao.UserDao;
import cz.vsmie.krist.pms.dto.User;
import cz.vsmie.krist.pms.exception.UserEmailNotAvailable;
import cz.vsmie.krist.pms.exception.UserException;
import cz.vsmie.krist.pms.exception.UserNameNotAvailable;
import cz.vsmie.krist.pms.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
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
