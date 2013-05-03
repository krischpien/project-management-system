package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author Jan Krist
 */

@Entity
@Table(name="user_details",
        uniqueConstraints=@UniqueConstraint(columnNames={"user_name", "email"}))
public class UserDetails implements Serializable {
    
    @Id @GeneratedValue
    private long id;
    
    
    @Column(name="user_name")
    @Size(min=3, max=50, message="Uživatelské jméno musí být v rozsahu 3-50 alfanumerických znaků") //spring validace
    private String name;
    private String email;
//    @Column(columnDefinition="BINARY(28)") // SHA-224 hash
    @Size(min=5, max=55, message="Uživatelské heslo musí být v rozsahu 5-55 alfanumerických znaků") //spring validace
    private String password;
    
    @ManyToMany
    @JoinTable(name="user_in_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Collection<UserRole> roles = new ArrayList<UserRole>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UserRole> roles) {
        this.roles = roles;
    }
    
    

}
 