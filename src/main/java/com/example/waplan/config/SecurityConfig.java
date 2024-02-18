package com.example.waplan.config;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.example.waplan.jwt.JWTFilter;
import com.example.waplan.jwt.JWTUtil;
import com.example.waplan.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //	 // 스프링 시큐리티 기능 비활성화
	@Bean
	public WebSecurityCustomizer configure() {
		return (web -> web.ignoring()
				.requestMatchers(toH2Console())
				.requestMatchers("/static/**", "/h2-console/**", "/favicon.ico", "/error", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**")
		);
	}

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeHttpRequests((auth) -> auth
//                .requestMatchers("/", "/login", "/signup").permitAll()
//                .requestMatchers("/admin").hasRole("ADMIN")
//                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//            );
//
//        http
//            .formLogin((auth) -> auth.loginPage("/login")
//                .loginProcessingUrl("/loginProc")
//                .permitAll()
//            );
//
//        // 잠시 설정
//        http
//            .csrf((auth) -> auth.disable());
//
//
//        http
//            .sessionManagement((auth) -> auth
//                .maximumSessions(2)
//                .maxSessionsPreventsLogin(true));
//
//        http
//            .sessionManagement((auth) -> auth
//                .sessionFixation().changeSessionId());
//
//        return http.build();
//    }

    private final AuthenticationConfiguration authenticationConfiguration;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login", "/signup").permitAll()
                .requestMatchers("/").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            );

        http
            .csrf((auth) -> auth.disable());

        http
            .formLogin((auth) -> auth.disable());

        http
            .httpBasic((auth) -> auth.disable());

        http
            .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        //AuthenticationManager()와 JWTUtil 인수 전달
        http
            .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
