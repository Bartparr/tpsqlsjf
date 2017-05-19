/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.formation.sql.models;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import fr.formation.sql.Sanspapier;
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

@ManagedBean(name="connectSanspapier")
@SessionScoped
public class ConnectSanspapier {
    
    public static List<Sanspapier> getPerson() throws ClassNotFoundException, SQLException {
       
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
        List<Sanspapier> ListSanspapier = new ArrayList<>();
       
        PreparedStatement pstmt = connect.prepareStatement("select * from sanspapiers");
        ResultSet rs = pstmt.executeQuery();
               
               while (rs.next()) {            
                Sanspapier newPersonn = new Sanspapier();                       
                    newPersonn.setId(rs.getInt("Id"));
                    newPersonn.setName(rs.getString("Name"));
                    newPersonn.setSurname(rs.getString("Surname"));
                    newPersonn.setDescription(rs.getString("description")); 
                    newPersonn.setPays(rs.getString("pays"));
                    ListSanspapier.add(newPersonn);        
               }
           //fermeture des ressources
           rs.close();
           pstmt.close();
           connect.close();    
           return ListSanspapier;//on retourne la liste aves toutes les personnes et leurs infos
       
   }
    
   //addPerson avec nationalité
     public static void addPerson(String name,String surname,String pays,String description){
        
        try{
        	
        	Class.forName("com.mysql.jdbc.Driver");
                 
                String url = "jdbc:mysql://localhost:3306/bdd";        
                String username = "root";
                String password = "root";
	         
	        Connection cnx = (Connection) DriverManager.getConnection(url,username,password);
	         	      	                
	        String query = "INSERT INTO sanspapiers (name,surname,pays,description) VALUES (?,?,?,?)";
	        PreparedStatement preparedStmt = (PreparedStatement) cnx.prepareStatement(query);	        
	        		        
	        preparedStmt.setString(1, name);
	        preparedStmt.setString(2, surname);
	        preparedStmt.setString(3, pays);
                preparedStmt.setString(4, description);
	        preparedStmt.executeUpdate();
            
	        preparedStmt.close();
            cnx.close();
            
        }catch(Exception e){
            System.out.println("erreur ");
        }
       //return "welcome";
    }
     
       public void removePerson(int ID1){

       Connection cnx = null;

            try{

                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver ok");                

                cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd","root","root");
                System.out.println("Connexion ok");               

                Statement stat = (Statement)cnx.createStatement();
                System.out.println("Statement ok");
                System.out.println("------------------");             

                String query = "DELETE FROM `sanspapiers` WHERE Id=?";
               PreparedStatement preparedStmt = (PreparedStatement) cnx.prepareStatement(query);          

               preparedStmt.setInt(1,ID1);          
               preparedStmt.executeUpdate();               

                stat.close();
                cnx.close();                

            }catch(Exception e){
                System.out.println("erreur");
            }

        }
    
    
}
