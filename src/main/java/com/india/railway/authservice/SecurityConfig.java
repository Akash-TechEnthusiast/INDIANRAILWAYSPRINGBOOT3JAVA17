package com.india.railway.authservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.india.railway.service.CustomUserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // extends WebSecurityConfigurerAdapter
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // @Autowired
    // private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
         * http
         * .csrf().disable() // Optional: Disable CSRF only for testing
         * .authorizeRequests()
         * .requestMatchers("/login", "/register", "/").permitAll() // Allow access to
         * login and register
         * .anyRequest().authenticated(); // Require authentication for all other URLs
         * 
         * return http.build();
         */

        http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorizeRequests -> {
                    try {
                        authorizeRequests
                                .requestMatchers("/", "/authenticate", "/products", "/products/suggest").permitAll() // Allow
                                                                                                                     // access
                                                                                                                     // to
                                                                                                                     // root
                                // URL
                                .anyRequest().authenticated(); // All other URLs require authentication
                        // .and()
                        // .oauth2Login()
                        // .defaultSuccessUrl("/home", true); // Redirects to /home after successful
                        // login

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter,
                        UsernamePasswordAuthenticationFilter.class);

        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    /*
     * @Bean
     * public AuthenticationProvider authenticationProvider() {
     * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
     * provider.setUserDetailsService(userDetailsService);
     * provider.setPasswordEncoder(new BCryptPasswordEncoder());
     * return provider;
     * }
     */

    /*
     * 
     * @Bean
     * public void configureGlobal(AuthenticationManagerBuilder auth) throws
     * Exception {
     * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
     * );
     * }
     * 
     * @Bean
     * public void configure(AuthenticationManagerBuilder auth) throws Exception {
     * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
     * );
     * }
     */

    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * UserDetails user = User.withDefaultPasswordEncoder()
     * .username("user") // Username
     * .password("user") // Password (should be encoded in production)
     * .roles("USER") // Assign role
     * .build();
     * 
     * return new InMemoryUserDetailsManager(user);
     * }
     */

    /*
     * @Bean
     * public AuthenticationProvider authenticationProvider() {
     * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
     * provider.setUserDetailsService(customUserDetailsService);
     * provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
     * return provider;
     * }
     */

    // below code is for authentication purpose
    /*
     * @Override
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception
     * {
     * auth.userDetailsService(customUserDetailsService).passwordEncoder(
     * passwordEncoder());
     * 
     * }
     * 
     * // below code is for authorization purpose
     * 
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * // for test environment
     * // http.csrf().disable().authorizeRequests().anyRequest().permitAll();
     * // for production environment
     * 
     * http.csrf().disable()
     * .authorizeRequests()
     * .antMatchers("/", "/welcome", "/authenticate", "/register",
     * "/products", "/products/name/{name}")
     * // .hasRole("admin").antMatchers("/")
     * .permitAll()
     * .anyRequest().authenticated()
     * // for any other request it should be validated
     * .and().sessionManagement()
     * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     * 
     * http.addFilterBefore(jwtRequestFilter,
     * UsernamePasswordAuthenticationFilter.class);
     * 
     * }
     * 
     * 
     * 
     * 
     * @Bean
     * 
     * @Override
     * public AuthenticationManager authenticationManager() throws Exception {
     * return super.authenticationManagerBean();
     * }
     * 
     * 
     * // @Bean
     * // public PasswordEncoder passwordEncoder() {
     * // return NoOpPasswordEncoder.getInstance();
     * // return new BCryptPasswordEncoder();
     * // }
     */

    // System.out.println("test");
    // "/products","/products/name/{name}"

}
