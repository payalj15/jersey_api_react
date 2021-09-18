/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.heroku;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.jsonwebtoken.Jwts;   
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;
/**
 *
 * @author shiv
 */
@Path("/hello")
public class Hello {
   
    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    
    public List<Message> getMsg() {
    
        List<Message> mes=new ArrayList<>();
    
        Database_01 db1=new Database_01();
        mes=db1.getUsers();
        return mes;
       
        //return Response.status(200).entity(abc).build();
    }
    @GET
    @Path("/get_id")
    @Produces(MediaType.APPLICATION_XML)
    public Message getMsg2(@QueryParam("id") int id) {
    
        Message mes=new Message();
    
        Database_01 db1=new Database_01();
        mes=db1.getUser(id);
        return mes;
       
        //return Response.status(200).entity(abc).build();
    }
   
    
    @Path("/ins")
    @POST
    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    
    
    
    
    public String insert(@FormParam("fn") String firstName,@FormParam("ps") String password, @FormParam("ln") String lastName ,@FormParam("em") String Email ,@FormParam("rl") String Role) throws SQLException {
    
        Message mes=new Message();
        Database_01 db1=new Database_01();
        mes=db1.insert(firstName,password,lastName,Email,Role);
        
        
        @SuppressWarnings("deprecation")
                
                
                
        String token = Jwts.builder().claim("fn",firstName)
                .claim("ln", lastName)
                .claim("ps", password)
                .claim("em",Email)
                .claim("rl", Role)
                .signWith(SignatureAlgorithm.HS512, "MTIzNDU2Nzg=").compact();
      
        
    
        
   
    
        return token;
    
    }
        
     
    
    @DELETE
    @Path("/delete/{id}/")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id){
        Database_01 db1=new Database_01();
        Boolean abc=db1.delete(id);
        if(abc==true){
            return "deleted successfully";
        }
        else{
            return "record with id doesn't exist";
        }
        
    }
}
