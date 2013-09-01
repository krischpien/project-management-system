package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.UserRole;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface RoleDao extends GenericDao<UserRole> {

    /**
     * Get main roles in database (e.g. ROLE_PROVIDER, ROLE_CUSTOMER)
     * @return role
     */
    public Collection<UserRole> getMainRoles();
    
    /**
     * Get all roles, which can be assigned to main roles 
     * (all roles but main - ROLE_PROVIDER, ROLE_CUSTOMER)
     * @return 
     */
    public Collection<UserRole> getAssignableRoles();
}
