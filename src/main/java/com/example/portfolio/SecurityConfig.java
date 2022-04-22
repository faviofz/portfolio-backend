//package com.example.portfolio;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.example.portfolio.service.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable().httpBasic().and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                /*.and().addFilter(jwtAuthorizationFilter())*/;
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//                .anyRequest().authenticated();
//
//    }
//
//}
