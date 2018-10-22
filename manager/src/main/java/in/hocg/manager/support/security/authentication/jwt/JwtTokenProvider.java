package in.hocg.manager.support.security.authentication.jwt;

import in.hocg.util.DateKit;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@Slf4j
public class JwtTokenProvider {
    
    // 24h
    private int expiration = 1000 * 60 * 60 * 24;
    private String secret = "hocgin";
    
    public String generate(Authentication authentication) {
        LocalDateTime dateTime = LocalDateTime.now().plus(expiration, ChronoUnit.MILLENNIA);
        return Jwts.builder().setSubject(authentication.getName())
                .setExpiration(DateKit.as(dateTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
