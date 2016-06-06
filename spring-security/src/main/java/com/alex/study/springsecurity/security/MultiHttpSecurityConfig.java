package com.alex.study.springsecurity.security;

import com.alex.study.springsecurity.service.JwtUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 多个Spring security配置
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class MultiHttpSecurityConfig {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                    .httpBasic();
        }
    }

    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api2/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin();
        }
    }

    @Configuration
    @Order(3)
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

        @Autowired
        private JwtAuthenticationFailEntryPoint unAuthenticationEntryPoint;
        @Autowired
        private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

        @Resource
        private JwtUserDetailsServiceImpl userDetailsService;

        @Bean
        public AppUserAuthenticationProvider appUserAuthenticationProvider() {
            return new AppUserAuthenticationProvider();
        }

        // 设置不拦截规则
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/static/**", "/**/*.jsp");
        }

        // 设置拦截规则
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            logger.debug("configure");

            http
                    // we don't need CSRF becouse our token is invulnerable
                    .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unAuthenticationEntryPoint).and()
                    // don't create session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/hello").authenticated()

                    .anyRequest().permitAll();

            // Custom JWT based security filter
            http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

            // disable page caching
            http.headers().cacheControl();
        }

        @Bean
        public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
            JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
            authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
            authenticationTokenFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
            return authenticationTokenFilter;
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            AuthenticationManager authenticationManager = super.authenticationManagerBean();
            return authenticationManager;
        }

    }
}
