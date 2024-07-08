package com.example.waplan.config;

import com.example.waplan.security.CustomUserDetailsService;
import com.example.waplan.security.LoginFilter;
import com.example.waplan.security.LoginSuccessHandler;
import com.example.waplan.security.RestAuthenticationEntryPoint;
import com.example.waplan.security.TokenAuthenticationFilter;
import com.example.waplan.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
            .requestMatchers(PathRequest.toH2Console());
    }


    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    private final CustomUserDetailsService customUserDetailsService;
    private final TokenProvider tokenProvider;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(Collections.singletonList(authProvider));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
            .authorizeHttpRequests(authorize -> authorize
                //h2 설정
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                //.requestMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .requestMatchers(antMatcher(HttpMethod.POST, "/api/auth/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.POST, "/api/user/signup")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.POST, "/api/user/reset_password")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.POST, "/api/user/find_userId")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.POST, "/api/user/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.GET, "/api/user/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.PUT, "/api/user/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.POST, "/goal/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.GET, "/goal/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.DELETE, "/goal/**")).hasRole("USER")
                .requestMatchers(antMatcher(HttpMethod.GET, "/cheering")).hasRole("USER")
                .anyRequest().authenticated()
            );

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/api/auth/login");
        loginFilter.setAuthenticationManager(authenticationManager());
        loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler(tokenProvider));
        return loginFilter;
    }
}
