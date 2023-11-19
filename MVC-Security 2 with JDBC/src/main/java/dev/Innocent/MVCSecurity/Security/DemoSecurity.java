package dev.Innocent.MVCSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurity {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        // Define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
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

    /**
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails john = User.builder()
                .username("john").password("{noop}test123").roles("EMPLOYEE").build();

        UserDetails mary = User.builder()
                .username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();

        UserDetails susan = User.builder()
                .username("susan").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    } */
}
