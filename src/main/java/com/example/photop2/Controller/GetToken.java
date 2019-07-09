package com.example.photop2.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import static com.example.photop2.Security.SecurityConstants.SECRET;
import static com.example.photop2.Security.SecurityConstants.TOKEN_PREFIX;

public class GetToken {

    public String token ;

    public GetToken(String token){this.token = token;}

    public int getUserId(){

        Integer id = null ;
        if (token != null) {
            // parse the token.
            id = JWT.require(Algorithm.HMAC512(SECRET.getBytes())) //Zwracany typ taki sam jak typ claima
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaim("userId").asInt();
        }
       return id;
    }

    public Boolean IsModertor(){
        Boolean isModerator = null ;
        if (token != null) {
            // parse the token.
            isModerator = JWT.require(Algorithm.HMAC512(SECRET.getBytes())) //Zwracany typ taki sam jak typ claima
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaim("moderator").asBoolean() ;
        }
        return isModerator;
    }
}
