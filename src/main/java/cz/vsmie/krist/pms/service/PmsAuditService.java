package cz.vsmie.krist.pms.service;
/**
 *
 * @author Jan Krist
 */
public interface PmsAuditService{

    /**
     * Check if project date of deadline (cron action)
     */
    public void checkProjectsDeadline();
}
