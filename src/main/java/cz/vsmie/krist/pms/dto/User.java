package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    @Size(min=3, max=50, message="Uživatelské jméno musí být v rozsahu 3-50 alfanumerických znaků") //spring validace
    private String name;
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Chybná emailová adresa. Vzor: jmeno@email.cz")
    private String email;
    @Column(columnDefinition="BINARY(80)") // SHA-256 hash => 32B hash + 8B salt
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
    private Set<UserRole> roles = new HashSet<UserRole>();
    
    @ManyToMany(mappedBy="authorizedUsers")
    @Cascade(CascadeType.ALL)
    private Set<Project> projects = new HashSet<Project>();
    
    @OneToMany(mappedBy="author", orphanRemoval=true)
    @Cascade({CascadeType.ALL})
    private Set<Comment> comments = new HashSet<Comment>();
    
    
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
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
    
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
    
    @Override
    public String toString(){
        return "Uživatel#"+id+": "+name+"+"+email;
    }

    

 
    

}
 