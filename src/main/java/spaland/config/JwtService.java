package spaland.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    public final static long TOKEN_VALIDATION_SECOND = 60 * 10; // 1분
    public final static int REFRESH_TOKEN_VALIDATION_SECOND = 60  * 60 * 24 * 7; // 7일

    public final static String COOKIE_NAME = "refresh_token";


    private static final String SECRET_KEY = "42264528482B4D6251655468576D5A7134743777217A25432A462D4A404E6352";

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshToken(String jwt) {
        return Jwts
                .builder()
                .setClaims(extractAllClaims(jwt))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 ))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return ( username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    public Boolean isRefreshTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return ( username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    private Boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public Long getExpiration(String jwt){
        Long now = new Date().getTime();
        return (extractExpiration(jwt).getTime() - now);
    }


    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
//        byte[] key = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(key);
    }

}
