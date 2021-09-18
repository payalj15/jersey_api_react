/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.heroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shiv
 */
@XmlRootElement
public class Database_01 {
         static final String DB_URL ="jdbc:postgresql://ec2-3-219-111-s26.compute-1.amazonaws.com:5432/dc6ogq386kd4vk";
   static final String DB_USER = "wtkpjcmneqxcth";
   static final String DB_PASSWD = "******";
  
   
   int idint;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String abc;
    public List<Message> getUsers(){

        List<Message> mes=new ArrayList<>();


        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
         connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
         statement=connection.createStatement();
         resultSet=statement.executeQuery
            ("SELECT * FROM COUNSELOR3");
         while(resultSet.next()){
           Message m=new Message();
            m.setid(resultSet.getInt(1));
            m.setFirstName(resultSet.getString(2));
            m.setPassword(resultSet.getString(3));
            m.setLastName(resultSet.getString(4));
            m.setEmail(resultSet.getString(5));
            m.setRole(resultSet.getString(6));
            mes.add(m);
         }

      }


          catch(SQLException ex){
      }
          finally{
         try {
            resultSet.close();
            statement.close();
            connection.close();
         } catch (SQLException ex) {
         }
      }



       return mes;
    }

    public Message getUser(int id){
        Message m=new Message();
        //List<Message> mes=new ArrayList<>();
         try{
         DriverManager.registerDriver(new org.postgresql.Driver());
         connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         
         connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
         statement=connection.createStatement();
         resultSet=statement.executeQuery("SELECT * FROM COUNSELOR3 where id="+id);
         if(resultSet.next()){

           m.setid(resultSet.getInt(1));
            m.setFirstName(resultSet.getString(2));
            m.setPassword(resultSet.getString(3));
            m.setLastName(resultSet.getString(4));
            m.setEmail(resultSet.getString(5));
            m.setRole(resultSet.getString(6));

         }

      }


          catch(SQLException ex){
      }
          finally{
         try {
            resultSet.close();
            statement.close();
            connection.close();
         } catch (SQLException ex) {
         }
      }

     return m;
    }
    public Message insert(String fnam,String pas,String lnam,String em,String role)
    {
        Statement st2 = null;
        
        
        
        Message m=new Message();
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
         connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         st2=connection.createStatement();
         //statement=connection.createStatement();

         String query="INSERT INTO COUNSELOR3 (FIRSTNAME,PASSWORD, LASTNAME, EMAIL, ROLES) VALUES (?,?,?,?,?)";
         PreparedStatement  statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         statement.setString(1, fnam);
         statement.setString(2, pas);
         statement.setString(3, lnam);
         statement.setString(4, em);
         statement.setString(5, role);
         
         int numero = statement.executeUpdate();

         int idnew=0;
ResultSet rs = statement.getGeneratedKeys();

//
         if(rs.next()){

            idnew=rs.getInt(1);
            System.out.println("idnew is"+idnew);
           
         }
         if(idnew!=0){
         resultSet=st2.executeQuery("SELECT * FROM COUNSELOR3 WHERE ID ="+idnew);
         if(resultSet.next()){
            m.setid(resultSet.getInt(1));
            m.setFirstName(resultSet.getString(2));
            m.setPassword(resultSet.getString(3));
            m.setLastName(resultSet.getString(4));
            m.setEmail(resultSet.getString(5));
            m.setRole(resultSet.getString(6));
         }
         }



        }

         catch(SQLException ex){
             System.out.println("-------------------------------------->>>>>>>>>>> exception"+ex);
      }
          finally{
         try {
            //resultSet.close();
            //statement.close();
            connection.close();
         } catch (SQLException ex) {
              System.out.println("--------------------------------------------->>>>>>>>>>>exception"+ex);
         }

    }
    return m;
}
    public Boolean delete(int id)
    {
        Boolean suc=false;
        Message m=new Message();
        try{
         DriverManager.registerDriver(new org.postgresql.Driver());
         connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         
         connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         statement=connection.createStatement();
         PreparedStatement ps2 = connection.prepareStatement("SELECT id FROM COUNSELOR3 WHERE id = ?");
         ps2.setInt(1, id);
         
         ResultSet rs = ps2.executeQuery();
         
         
         if (rs.next()) {
      
         String SQLstring="DELETE FROM COUNSELOR3 WHERE id="+id;
         
         int deleted = statement.executeUpdate(SQLstring);
         
        if (deleted != 0) {
            System.out.println("----------------------->>>>>>>>"+deleted);

            suc=true;
            return suc;
}
} 
        }
      
         catch(SQLException ex){
             System.out.print(ex);
         }
      
          finally{
         try {
            //resultSet.close();
            //statement.close();
            connection.close();
         } catch (SQLException ex) {
         }

    }
        return suc;
}
    public Boolean login(String un,String ps){
        Message m=new Message();
        Boolean suc=false;
        try{
         DriverManager.registerDriver(new org.postgresql.Driver());
         connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         
         //connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
         statement=connection.createStatement();
         PreparedStatement ps2 = connection.prepareStatement("SELECT Firstname FROM COUNSELOR3 WHERE Password = ? AND Firstname = ?");
         ps2.setString(1, ps);
         ps2.setString(2, un);
         ResultSet rs = ps2.executeQuery();
         
         if (rs.next()) {
             suc=true;
         }
         
         }
        
         catch(SQLException ex){
             System.out.print(ex);
         }
        return suc;
    }
}
