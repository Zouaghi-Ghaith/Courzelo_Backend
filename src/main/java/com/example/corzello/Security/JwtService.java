package com.example.corzello.Security;

import com.example.corzello.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="aRaMyD+Bt7C7U3sBj7y4E9PZ+d5KOXC1hM0S/270D6qgAQPnEiwxpJHRvZufT89uNH/c28YMD6A6Zrm9iMUFintbtV6JWFSacTEgb4+6Z63gNHuQWE4j4f26evX/9bZCTuhxjqnWiK2dGMEZ9ZUwtV0AzaGDANqu+eZ9Y0rOT246dByhmOr1AV99sPQ+1r+2SvRokR0EL9MTnqncyACm6SjLgnmuBP6wVObMSQTQ6qdFnf4VCSPXHMwu3i34suJVhGhsABQrODx84L7jGImiIp+zTH5uKw9RPRZLnPzdASWeUNhXaIDvQ1vm0Ms4Cd6ZSF0tbRb70FGtOy4JS08tS+HxCP/EYZhifdaYuT3RUrE=\n";

    public String extractUsername(String token) {

        return extractClaim(token,Claims::getSubject) ;
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(
            Map<String,Object>extraClaims,
            UserDetails userDetails
    ) {
        extraClaims.put("role", userDetails.getAuthorities().toString());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token , UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
        
    }

    private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keybytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybytes);
    }
}
