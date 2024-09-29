package com.controller.bean;

import com.model.pojo.Project;
import com.model.pojo.ProjectWorker;
import com.model.pojo.Task;
import com.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pc
 */
@Named(value = "viewProjectBean")
@ViewScoped
public class ViewProjectBean {
    private Integer projectId;
    private Project project;
    private ProjectService projectService;
    
    @Inject
    private AuthBean authBean;
    
    /**
     * Creates a new instance of ViewProjectBean
     */
    public ViewProjectBean() {
    }
    
    @PostConstruct
    public void init() {
        this.projectService = new ProjectService();
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String projectIdParam = params.get("project_id");
        
        if (projectIdParam != null) {
            this.projectId = Integer.parseInt(projectIdParam);
            loadProject();
        }
    }
    
    private void loadProject() {
        if (authBean.getLoggedInUser() == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "You're not logged in", null));
        }
        
        if(projectId != null) {
            this.project = projectService.getProjectById(authBean.getLoggedInUser(), projectId);
            if (this.project == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Project not found", null));
            }
        }
    }

    public String getManager() {
        if (project == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Project is null", null));
            return null;
        }
        
        Set<ProjectWorker> pw = this.project.getProjectWorkers();
        for (ProjectWorker worker : pw) {
            if (worker.getRole().equals("manager")) {
                return worker.getUser().getFirstName() + " " + worker.getUser().getLastName();
            }
        }
        
        return null; // Project doesn't have a manager?
    }
    
    public String navigateToTaskForm() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("project", this.project);
        return "task-form?faces-redirect=true";
    }
    
    public List<Task> getTaskList() {
        return new ArrayList<>(this.project.getTasks());
    }
    
    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
    
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    
}
