package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Jan Krist
 */
@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
    
    @Id
    private long id;
    @Column(name="role_name")
    private String name;
    private String description;
    @ManyToMany(mappedBy="roles")
    private Collection<UserDetails> users = new ArrayList<UserDetails>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDetails> users) {
        this.users = users;
    }
    

}
