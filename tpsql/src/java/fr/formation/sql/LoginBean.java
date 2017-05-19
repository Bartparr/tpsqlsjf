/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.formation.sql;

import fr.formation.sql.models.ConnectUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ib
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
    
    private boolean loggedIn;
    NavigationBean navigationBean;
    
    public String doLogin(String name, String password) throws ClassNotFoundException, SQLException{
        List<User> persons=new ArrayList<User>();        
        persons = ConnectUser.getPerson(); 
            
        for(User person: persons){  
            // Successful login        
            if (person.getName().equals(name) && person.getPassword().equals(password)) {
                loggedIn = true;
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser",person);
                return "/secured/listeusers.xhtml";
            }
        }    
               // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "login";      
    }
    
        /**
     * Logout operation.
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "/login.xhtml";
         
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
 
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
    

