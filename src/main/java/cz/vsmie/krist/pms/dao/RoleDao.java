package cz.vsmie.krist.pms.dao;

import cz.vsmie.krist.pms.dto.UserRole;
import java.util.Collection;

/**
 *
 * @author Jan Krist
 */
public interface RoleDao extends GenericDao<UserRole> {

    public Collection<UserRole> getMainRoles();
    public Collection<UserRole> getAssignableRoles();
}
