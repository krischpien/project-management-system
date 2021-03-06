package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    
    @Id @GeneratedValue
    private Long id;
    @Column(name="role_name")
    private String name;
    private String description;
    //     .::RELACE::. 
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>();

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        UserRole checkedRole = (UserRole) obj;
        return this.getName().equals(checkedRole.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null)? 0 : this.name.hashCode());
        return result;

    }
    
    
    

}
