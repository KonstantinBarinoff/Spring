package ru.barinoff.springsecurity1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.barinoff.springsecurity1.model.Permission;
import ru.barinoff.springsecurity1.model.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority(Permission.DEVELOPERS_READ.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/**").hasRole(Role.ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority(Permission.DEVELOPERS_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Role.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasAnyAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder().
                        username("admin").
                        password(passwordEncoder().encode("admin")).
//                        roles(Role.ADMIN.name()).
                        authorities(Role.ADMIN.getAuthorities()).
                        build(),
                User.builder().
                        username("user").
                        password(passwordEncoder().encode("user")).
//                        roles(Role.USER.name()).
                        authorities(Role.USER.getAuthorities()).
                        build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
