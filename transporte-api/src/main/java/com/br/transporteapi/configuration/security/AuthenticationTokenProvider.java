package com.br.transporteapi.configuration.security;

import com.br.transporteapi.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class AuthenticationTokenProvider {

    @Value("${transporte-api.jwt.expiration}")
    private String expiration;

    @Value("${transporte-api.jwt.key}")
    private String key;

    private static final String ISSUER = "transporte-api";

    @Autowired
    private AuthenticationService authenticationService;

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }


    public String createToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Long.parseLong(this.expiration));

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

    }

    public Authentication getAuthentication(String token) {
        Usuario usuario = (Usuario) this.authenticationService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody().getSubject();
    }

    public String recoverToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) return null;

        return token.substring(7);

    }

    public boolean isValidToken(String token) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
        } catch (SignatureException ex) {
            return false;
        }

        if (claimsJws.getBody().getExpiration().before(new Date()) || token == null) return false;
        else return true;

    }

}
