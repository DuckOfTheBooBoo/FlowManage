package com.model.pojo;
// Generated Sep 28, 2024 1:16:40 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="flow_manage"
)
public class User  implements java.io.Serializable {


     private Integer id;
     private String firstName;
     private String lastName;
     private String email;
     private String password;
     private Set<User> usersForUserIdB = new HashSet<User>(0);
     private Set<ProjectWorker> projectWorkers = new HashSet<ProjectWorker>(0);
     private Set<Task> tasks = new HashSet<Task>(0);
     private Set<User> usersForUserIdA = new HashSet<User>(0);

    public User() {
    }

	
    public User(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }
    
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }
    
    public User(String firstName, String lastName, String email, String password, Set<User> usersForUserIdB, Set<ProjectWorker> projectWorkers, Set<Task> tasks, Set<User> usersForUserIdA) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.password = password;
       this.usersForUserIdB = usersForUserIdB;
       this.projectWorkers = projectWorkers;
       this.tasks = tasks;
       this.usersForUserIdA = usersForUserIdA;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="first_name", nullable=false, length=50)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="last_name", length=50)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="email", nullable=false, length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="password", nullable=false)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch=FetchType.LAZY, targetEntity = User.class)
    @JoinTable(name="contact", catalog="flow_manage", joinColumns = { 
        @JoinColumn(name="user_id_a", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="user_id_b", nullable=false, updatable=false) })
    public Set<User> getUsersForUserIdB() {
        return this.usersForUserIdB;
    }
    
    public void setUsersForUserIdB(Set<User> usersForUserIdB) {
        this.usersForUserIdB = usersForUserIdB;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user", targetEntity = ProjectWorker.class, cascade = CascadeType.ALL)
    public Set<ProjectWorker> getProjectWorkers() {
        return this.projectWorkers;
    }
    
    public void setProjectWorkers(Set<ProjectWorker> projectWorkers) {
        this.projectWorkers = projectWorkers;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user", targetEntity = Task.class, cascade = CascadeType.ALL)
    public Set<Task> getTasks() {
        return this.tasks;
    }
    
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @ManyToMany(fetch=FetchType.LAZY, targetEntity = User.class)
    @JoinTable(name="contact", catalog="flow_manage", joinColumns = { 
        @JoinColumn(name="user_id_b", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="user_id_a", nullable=false, updatable=false) })
    public Set<User> getUsersForUserIdA() {
        return this.usersForUserIdA;
    }
    
    public void setUsersForUserIdA(Set<User> usersForUserIdA) {
        this.usersForUserIdA = usersForUserIdA;
    }
}
