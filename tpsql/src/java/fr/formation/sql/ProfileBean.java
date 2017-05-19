/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.formation.sql;

import fr.formation.sql.models.ConnectUser;
import java.io.IOException;
import javax.annotation.PostConstruct;
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
@ManagedBean(name="profileBean")
@SessionScoped
public class ProfileBean {
    
    
    private String theme;
    private String header;
    
    
    public String setProfile(String theme, int id) throws IOException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession(true);
        User currentUser;
        currentUser = (User) session.getAttribute("currentUser");        
        ConnectUser.modifyTheme(theme,id);
        currentUser.setTheme(theme);
        return "listeimmigre";  
    }
    
    @PostConstruct
    public void init(){
         theme="dark";
         header="yes";
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }
    
}
