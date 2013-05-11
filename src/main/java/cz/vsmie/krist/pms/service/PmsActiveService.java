package cz.vsmie.krist.pms.service;
/**
 *
 * @author Jan Krist
 */
public abstract class PmsActiveService{
    
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
