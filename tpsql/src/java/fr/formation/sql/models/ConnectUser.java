/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.formation.sql.models;

import com.mysql.jdbc.Connection;
import fr.formation.sql.User;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ib
 */
@ManagedBean(name="connectUser")
@SessionScoped
public class ConnectUser {
    
    public static List<User> getPerson() throws ClassNotFoundException, SQLException {
       
       //connection à la base de données
       Connection connect = null;
       String url = "jdbc:mysql://localhost:3306/bdd";        
       String username = "root";
       String password = "root";
               
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            connect = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Connection établie"+connect);
        } catch (SQLException ex) {
            System.out.println("il y a une erreur");
            System.out.println(ex.getMessage());
        }
        //récupération des éléments de la BDD  
        List<User> ListPerson = new ArrayList<>();
       
        PreparedStatement pstmt = connect.prepareStatement("select * from users");
        ResultSet rs = pstmt.executeQuery();
               
               while (rs.next()) {            
                User newPersonn = new User();                       
                    newPersonn.setId(rs.getInt("Id"));
                    newPersonn.setName(rs.getString("Name"));
                    newPersonn.setSurname(rs.getString("Surname"));
                    newPersonn.setDescription(rs.getString("description")); 
                    newPersonn.setIsAdmin(rs.getString("isAdmin"));
                    newPersonn.setPassword(rs.getString("password"));
                    newPersonn.setTheme(rs.getString("theme"));
                    ListPerson.add(newPersonn);        
               }
           //fermeture des ressources
           rs.close();
           pstmt.close();
           connect.close();    
           return ListPerson;//on retourne la liste aves toutes les personnes et leurs infos
       
   }
    
       //addPerson avec nationalité
     public static void modifyTheme(String theme,int id){
        
        try{
        	
        	Class.forName("com.mysql.jdbc.Driver");
                 
                String url = "jdbc:mysql://localhost:3306/bdd";        
                String username = "root";
                String password = "root";
	         
	        Connection cnx = (Connection) DriverManager.getConnection(url,username,password);
	         	      	                
	        String query = "UPDATE users SET theme=? WHERE id=?";
	        PreparedStatement preparedStmt = (PreparedStatement) cnx.prepareStatement(query);	        
	        		        
	        preparedStmt.setString(1, theme);
                preparedStmt.setInt(2, id);

	        preparedStmt.executeUpdate();

            
	        preparedStmt.close();
            cnx.close();
            
        }catch(Exception e){
            System.out.println("erreur ");
        }
       //return "welcome";
    }
    
}
