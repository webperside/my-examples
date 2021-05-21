package com.webperside.couponservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final String ACTUATOR = "/actuator/**";
    private static final String[] SWAGGER_ENDPOINTS = {
            "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**"};


    private final CustomAuthenticationEntryPoint jwtCustomAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, ACTUATOR).permitAll()
                .antMatchers(SWAGGER_ENDPOINTS).permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtCustomAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/test-url/**");
//    }

}
