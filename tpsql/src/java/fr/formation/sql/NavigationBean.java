/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.formation.sql;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ib
 */
@ManagedBean(name="navigationBean")
@SessionScoped
public class NavigationBean implements Serializable{
    
    private static final long serialVersionUID = 1520318172495977648L;
 

    public String redirectToLogin() {
        return "login";
    }

    public String toLogin() {
        return "/login.xhtml";
    }
     

    public String redirectToAdd() {
        return "/add.xhtml?faces-redirect=true";
    }
     

    public String toAdd() {
        return "/add.xhtml";
    }
     

    public String redirectToProfile() {
        return "/profile.xhtml?faces-redirect=true";
    }
     

    public String toProfile() {
        return "/profile.xhtml";
    }
    
        /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/welcome.xhtml";
    }
    
    public String toListeimmigre() {
        return "/listeimmigre.xhtml";
    }
    
}
