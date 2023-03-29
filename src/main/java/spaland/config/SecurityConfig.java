package spaland.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtUtils, redisUtils), UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                .accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .requestMatchers("/api/auth/v1/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
//                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                .requestMatchers("/api/v1/**").authenticated()
                .requestMatchers("/**").permitAll()
        ;

        return http.build();

//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//            http
//                    .csrf()
//                    .disable()
//                    .authorizeHttpRequests()
//                    .requestMatchers("/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .authenticationProvider(authenticationProvider)
//                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//            http.logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/login")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("refreshToken","token","JSESSIONID");
//
//            return http.build();
//        }
    }

}
