/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.model.pojo.Project;
import com.model.pojo.ProjectWorker;
import com.model.pojo.User;
import com.service.ProjectService;
import com.service.UserService;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pc
 */
@Named(value = "contactBean")
@ViewScoped
public class ContactBean implements java.io.Serializable {

    private User targetUser;
    private String role;
    private Project project;
    private Integer projectId;
    private String userEmail;
    private ProjectService ps;
    private UserService us;
    
    @Inject
    private AuthBean authBean;
    
    /**
     * Creates a new instance of ContactBean
     */
    public ContactBean() {
    }
    
    @PostConstruct
    public void init() {
        us = new UserService();
        ps = new ProjectService();
        
        if(authBean.getLoggedInUser() == null) {
            System.err.println("UNAUTHORIZED");
        }
        
        if (projectId != null) {
            this.project = ps.getProjectById(authBean.getLoggedInUser(), projectId);
        }
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    
    public String addUserToProject() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if(userEmail == null || userEmail.isEmpty()) {
            ctx.addMessage("contact-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail field is empty.", null));
            return null;
        }
        
        User searchUser = us.getUserByEmail(userEmail);
        
        if (searchUser != null) {
            for (ProjectWorker pw : project.getProjectWorkers()) {
                if(pw.getUser().equals(searchUser)) {
                    ctx.addMessage("contact-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already existed in the project.", null));
                    return null;
                }
            }
            
            this.targetUser = searchUser;
        } else {
            ctx.addMessage("contact-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User doesn't exists.", null));
            return null;
        }
        
        boolean isSuccessful = ps.addUserToProject(project, searchUser, role);
        if (isSuccessful) {
            return "project?faces-redirect=true&amp;project_id="+projectId;
        }
        ctx.addMessage("contact-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to add user to the project.", null));
        return null;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectService getPs() {
        return ps;
    }

    public void setPs(ProjectService ps) {
        this.ps = ps;
    }

    public UserService getUs() {
        return us;
    }

    public void setUs(UserService us) {
        this.us = us;
    }
    
    
}
