package dev.Innocent.MVCSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurity {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails john = User.builder()
                .username("john").password("{noop}test123").roles("EMPLOYEE").build();

        UserDetails mary = User.builder()
                .username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();

        UserDetails susan = User.builder()
                .username("susan").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(config ->
                config
                        //  Control access based on role
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                // For Login and Logout Page
                .formLogin(form -> form.loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser").permitAll())
                .logout(logout-> logout.permitAll())
                // For Error handling page
                .exceptionHandling(config ->
                        config.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
