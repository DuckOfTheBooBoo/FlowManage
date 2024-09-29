/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author pc
 */
@ManagedBean(name = "navigationBean", eager = true)
@RequestScoped
public class NavigationBean {

    /**
     * Creates a new instance of NavigationBean
     */
    public NavigationBean() {
    }
    
    @ManagedProperty(value = "#{param.page}")
    private String page;
    
    @ManagedProperty(value = "#{param.projectId}")
    private Long projectId;
    
    public String getPage() {
        return this.page;
    }
    
    public void setPage(String page) {
        this.page = page;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    public String showPage() {
        System.err.println(page);
        if(page == null) {
            return "dashboard?faces-redirect=true";
        }
        
        if("project".equals(page)) {
            return "project?faces-redirect=true&amp;includeViewParams=true";
        }
        
        if("projects".equals(page)) {
            return "projects?faces-redirect=true";
        }
        
        if ("dashboard".equals(page)) {
            return "dashboard?faces-redirect=true";
        }
        
        if("project-form".equals(page)) {
            return "project-form?faces-redirect=true";
        }
        
        if("contact-form".equals(page)) {
            return "contact-form?faces-redirect=true&amp;includeViewParams=true";
        }
        
        if("task-form".equals(page)) {
            return "task-form?faces-redirect=true&amp;includeViewParams=true";
        }
        
        return null;
    }
}
