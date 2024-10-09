package jp.te4a.spring.boot.teamc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.te4a.spring.boot.teamc.service.LoginUserDetailsService;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private  LoginUserDetailsService  LoginUserDetailsService; 

    @Autowired
    public void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(LoginUserDetailsService).passwordEncoder(passwordEncoder());
    }

    // パスワード暗号化
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/webjars/**", "/css/**").permitAll()
                .requestMatchers("/loginForm").permitAll()
                .requestMatchers("/users").hasRole("ADMIN")
                .requestMatchers("/tools").permitAll()
                .requestMatchers("/tools/search").permitAll()
                .requestMatchers("/tools/create").hasRole("ADMIN")
                .requestMatchers("/tools/delete").hasRole("ADMIN")
                .requestMatchers("/tools/edit").hasRole("ADMIN")
                .anyRequest().authenticated() // 上記以外は認証が必要
            )
            .formLogin((login) -> login
                .loginProcessingUrl("/login")
                .loginPage("/loginForm")
                .failureUrl("/loginForm?error")
                .defaultSuccessUrl("/tools", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/loginForm")
            )
            // アクセス拒否時にエラーページを表示
            .exceptionHandling()
                .accessDeniedPage("/tools/access-denied");  // 管理者権限がないユーザーがアクセスした場合のリダイレクト先
            
        return http.build();
    }
    
}