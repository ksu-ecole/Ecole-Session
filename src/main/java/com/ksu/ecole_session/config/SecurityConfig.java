package com.ksu.ecole_session.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**"))
            .and()
                .headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
                .frameOptions().disable()
            .and()
                .authorizeRequests().antMatchers("/sign-up", "/sign-in", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/sign-in")
                .defaultSuccessUrl("/test", true)
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/favicon.ico")
        ;
    }
}






















