package cz.vsmie.krist.pms.service;
/**
 *
 * @author Jan Krist
 */
public abstract class AbstractActiveService implements ActiveService{
    
    boolean active = true;
    
    @Override
    public boolean isActive(){
        return active;
    }
    @Override
    public void setActive(boolean active){
        this.active = active;
    }
    

}
