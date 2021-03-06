package io.vscale.perpenanto.security.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 28.01.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@ComponentScan("io.vscale.perpenanto")
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;
    private AuthenticationProvider authenticationProvider;

    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                 .antMatchers("/user/**").hasAuthority("USER")
                 .antMatchers("/admin/**").hasAuthority("ADMIN")
                 .antMatchers("/confirm/**", "/get_file/**", "/catalog", "/index", "/register",
                                             "/css/**", "/js/**", "/fonts/**", "/").permitAll()
                 .antMatchers("/").permitAll()
                 .anyRequest().authenticated()
            .and()
                 .formLogin().loginPage("/login")
                 .usernameParameter("login")
                 .defaultSuccessUrl("/")
                 .failureUrl("/login?error")
                 .permitAll()
            .and()
                 .logout()
                 .logoutUrl("/logout")
                 .deleteCookies("remember-me")
                 .logoutSuccessUrl("/login")
                 .permitAll()
            .and()
                 .rememberMe().rememberMeParameter("remember-me")
                 .tokenValiditySeconds(86400)
            .and()
                 .csrf()
                 .disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService);
        auth.authenticationProvider(this.authenticationProvider);
    }

}
