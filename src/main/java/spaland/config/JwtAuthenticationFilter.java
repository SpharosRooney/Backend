package spaland.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private RedisTemplate<String, String> redisTemplate;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userId;
        final String refreshToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userId = jwtService.extractUsername(jwt);
//        refreshToken = jwtService.refreshToken(jwt);

        try {
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    String isLogout = redisTemplate.opsForValue().get(jwt);
                    if(ObjectUtils.isEmpty(isLogout)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
                else {
                    logger.warn("Cannot find username from access token"); //Todo 엑세스 토큰이 유효하지 않음
                }
            }

        } catch (Exception e) {
            //Todo 해당 유저를 찾을 수 없음
        }
//        try {
//            if (refreshToken != null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
//                if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
//                    UsernamePasswordAuthenticationToken authenticationRefreshToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    authenticationRefreshToken.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request)
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(authenticationRefreshToken);
//                } else {
//                    logger.warn("Cannot find refresh token"); //Todo refresh 토큰 유효 x
//                }
//            }
//        } catch (Exception e) {
//        // Todo 해당 유저를 찾을 수 없음
//        }
        filterChain.doFilter(request, response);
    }
}
