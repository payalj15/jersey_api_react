package com.example.heroku;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shiv
 */
@Path("/auth")
public class AuthController {
    
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
    
    @Path("/login")
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response success(@FormParam("un") String un,@FormParam("ps")String ps) throws URISyntaxException{
        
        Database_01 db=new Database_01();
        Boolean suc=db.login(un, ps);
        if(suc){
            URI targetURIForRedirection = new URI("../index4.html");
            return Response.seeOther(targetURIForRedirection).build();
    
        }
        else{
           URI targetURIForRedirection = new URI("../fail.html");
            return Response.seeOther(targetURIForRedirection).build();
        }
    }
}
