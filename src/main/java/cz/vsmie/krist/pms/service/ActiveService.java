package cz.vsmie.krist.pms.service;

/**
 *
 * @author Jan Krist
 */
public interface ActiveService {
    
    /**
     * Return state of service
     * @return true if active
     */
    boolean isActive();
    
    /**
     * Set state of service - enable/disable
     * @param active true for enable
     */
    void setActive(boolean active);

}
