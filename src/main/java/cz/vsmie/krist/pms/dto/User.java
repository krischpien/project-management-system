package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Jan Krist
 */

@Entity
@Table(name="user_details", uniqueConstraints={@UniqueConstraint(columnNames="user_name"),
                                               @UniqueConstraint(columnNames="email")})
public class User implements Serializable {


    @Id @GeneratedValue
    private Long id;
    
    @Column(name="user_name")
//    @Size(min=3, max=50, message="Uživatelské jméno musí být v rozsahu 3-50 alfanumerických znaků") //spring validace
    private String name;
    private String email;
//    @Column(columnDefinition="BINARY(80)") // SHA-256 hash => 32B hash + 8B salt
//    @Size(min=5, max=80, message="Uživatelské heslo musí být v rozsahu 5-80 alfanumerických znaků") //spring validace
    private String password;
    @Column(name="last_login")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name="last_ip")
    private String lastIp;
//     .::RELACE::. 
    @ManyToMany
    @JoinTable(name="user_in_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    @ForeignKey(name="fk_user_role", inverseName="fk_role_user")
    private Collection<UserRole> roles = new ArrayList<UserRole>();
    
    @ManyToMany(mappedBy = "authorizedUsers")
    private Collection<Project> projects = new ArrayList<Project>();
    
    
    public Long getId() {
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
    
    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
         if (obj == this) {
            return true;
        }
        User checkedUser = (User) obj;
        return this.getId().equals(checkedUser.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
        return result;

    }

 
    

}
 