package org.bstu.fit.security.jwt;

import io.jsonwebtoken.*;
import org.bstu.fit.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private UserDetailsService userDetailsService;
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    public String createToken(String username)
    {
        AuthUser authUser=null;
        UserDetails user=userDetailsService.loadUserByUsername(username);
        if(!(user instanceof AuthUser))
        {
            throw new UsernameNotFoundException("Token could not be created because user was not found");
        }
        authUser=(AuthUser)user;
        Claims claims= Jwts.claims().setSubject(username);
        claims.put("id",authUser.getId());
        claims.put("last_name",authUser.getLastName());
        claims.put("name",authUser.getFirstName());
        claims.put("username",authUser.getUsername());
        claims.put("email",authUser.getEmail());
        claims.put("photoUrl",authUser.getPhotoUrl());
        Date currentDate=new Date(System.currentTimeMillis());
        Date expireDate=new Date(System.currentTimeMillis() + SecurityJwtConstants.TOKEN_EXPIRED);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,SecurityJwtConstants.SECRET_KEY)
                .compact();

    }
    public Authentication getAuthentication(String token)
    {
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"");
    }
    public String getUsername(String token){
        return Jwts.parser().setSigningKey(SecurityJwtConstants.SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
    public String resolveToken(HttpServletRequest req)
    {
        String bearerToken=req.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public boolean validateToken(String token)
    {
        try{
            Jws<Claims> claimsJws=Jwts.parser().setSigningKey(SecurityJwtConstants.SECRET_KEY).parseClaimsJws(token);
            String jwtUsername=getUsername(token);
            UserDetails user=userDetailsService.loadUserByUsername(jwtUsername);
            if(claimsJws.getBody().getExpiration().before(new Date()) && user!=null){
                return false;
            }
            return true;
        }
        catch (JwtException | IllegalArgumentException ex)
        {
            throw new JwtAuthenticationException("Invalid jwt token");
        }
    }
}
