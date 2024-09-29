/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.UserDAO;
import com.model.pojo.Priority;
import com.model.pojo.Project;
import com.model.pojo.User;
import com.service.ProjectService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

/**
 *
 * @author pc
 */

@Named(value = "projectBean")
@RequestScoped
public class ProjectBean {
    
    private String title;
    private String description;
    private String priority;
    private Date deadline;
    private List<User> userList;
    private List<Priority> priorityList;
    private List<Project> projectList;
    private ProjectService ps = new ProjectService();
    
    @Inject
    private AuthBean authBean;  // Injected session-scoped bean
    /**
     * Creates a new instance of ProjectBean
     */
    public ProjectBean() {
    }
    
    @PostConstruct
    public void init() {
        this.priorityList = new ArrayList<>();
        priorityList.add(new Priority("Low", 1));
        priorityList.add(new Priority("Medium", 2));
        priorityList.add(new Priority("High", 3));
    }
    
    
    
    public List<Project> getProjectList() {
        Set<Project> projects = ps.getProjects(getAuthBean().getLoggedInUser());        
        
        if (projects == null) {
            return new ArrayList<Project>();
        }
        
        return new ArrayList<Project>(projects);
    }
    
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    public void setPriorityList(List<Priority> pl) {
        this.priorityList = pl;
    }
    
    public List<Priority> getPriorityList() {
        return this.priorityList;
    }
    
    public String saveProject() {
        User loggedUser = getAuthBean().getLoggedInUser();
        
        if (loggedUser == null) {
            return null;
        }
        
        int priorityInt = Integer.valueOf(priority);
        
        // Transaction are opened and closed in this, project is created in db
        Project newProject = ps.addProject(title, description, priorityInt, deadline, loggedUser);
        
        if (newProject == null) {
            System.err.println("Project is null");
            return null;
        }
        
        System.err.println(newProject);
        
        // And also this, but created project id is not found in this
        boolean isSuccessful = ps.addUserToProject(newProject, loggedUser, "manager");
        
        if (!isSuccessful) {
            System.err.println("Add user to project failed");
            return null;
        }
//        
        return "dashboard?face-redirect=true";
    }
}
