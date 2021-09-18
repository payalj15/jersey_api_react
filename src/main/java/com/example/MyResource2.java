package com.example;

import com.example.heroku.Database_01;
import com.example.heroku.Message;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource2 {

   
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
     @Path("/ins")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insert(@PathParam("fn") String firstName,@PathParam("ps") String password, @PathParam("ln") String lastName ,@PathParam("em") String Email ,@PathParam("rl") String Role) throws SQLException {
    
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
   
    
}
