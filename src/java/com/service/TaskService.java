/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.StatusDAO;
import com.dao.TaskDAO;
import com.model.pojo.Project;
import com.model.pojo.Status;
import com.model.pojo.Task;
import com.model.pojo.User;
import java.util.Date;

/**
 *
 * @author pc
 */
public class TaskService {
    private TaskDAO taskDAO = new TaskDAO();
    private StatusDAO statusDAO = new StatusDAO();
    
    public boolean addNewTask(User user, Project project, String title, String description, Date deadline, int priority) {
        Status onGoingStat = statusDAO.getStatusById(1);
        Task newTask = new Task(project, onGoingStat, user, title, description, priority, deadline);
        return taskDAO.addTask(newTask);
    }
}
