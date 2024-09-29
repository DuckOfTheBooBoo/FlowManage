/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.model.pojo.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author pc
 */
@Named(value = "utilBean")
@RequestScoped
public class UtilBean {    
    public UtilBean() {
    }
    
    public long getRemainingTime(Date date) {
        Date currentDate = new Date();
        long diffInMil = Math.abs(date.getTime() - currentDate.getTime());
        return TimeUnit.DAYS.convert(diffInMil, TimeUnit.MILLISECONDS);
    }
    
    public String getFullName(User user) {
        if (user == null || user.getFirstName() == null) {
            return "";
        }
        
        if (user.getLastName() != null) {
            return user.getFirstName() + " " + user.getLastName();
        }
        
        return user.getFirstName();
    }
}
