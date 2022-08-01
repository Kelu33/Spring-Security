package fr.wcs.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailService() throws Exception {
        UserDetails user = User.builder()
                .username("Steve")
                .password("{bcrypt}$2y$10$9V6GB3wjWmIeJbgfg6F2.OKLj/9ji5z0T93kj9.8Zbp3w1v.WEdh2")
                .roles("CHAMPION")
                .build();
        UserDetails admin = User.builder()
                .username("Nick")
                .password("{bcrypt}$2y$10$Y5LAL9M12cllsWRKE7nMYuGrTJrySBQaXJ9PPBQ3hvJOYHgc.nqn6")
                .roles("CHAMPION", "DIRECTOR")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        manager.createUser(admin);
        return manager;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain secretBasesFilterChain(HttpSecurity http) throws Exception {
        http
                .antMatcher("/secret-bases")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("DIRECTOR")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/");
    }

}
