package com.example.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val configuration: AuthenticationConfiguration,
    private val jwtUtils: JWTUtils
) {

    private val PUBLIC_GET_MATCHERS = arrayOf(
        "/topics",
        "/courses"
    )

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/users"
    )

    private val PRIVATE_GET_MATCHERS = arrayOf(
        "/users"
    )

    private val ALL_ROLES = arrayOf(
        SecurityRoles.ROLE_ADMIN,
        SecurityRoles.ROLE_READ_ONLY,
        SecurityRoles.ROLE_READ_WRITE
    )

    private val WRITE_ROLES = arrayOf(
        SecurityRoles.ROLE_ADMIN,
        SecurityRoles.ROLE_READ_WRITE
    )


//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .httpBasic()
//            .and()
//            .authorizeHttpRequests()
//
////            .antMatchers(HttpMethod.GET, *PUBLIC_GET_MATCHERS).permitAll()
////            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
////            .antMatchers(HttpMethod.GET, *PRIVATE_GET_MATCHERS).hasAuthority(
////                SecurityRoles.ROLE_READ_WRITE.toString()
////            )
//
//            .antMatchers("/login").permitAll()
//
//            .anyRequest().authenticated()
//            .and()
//            http.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtils = jwtUtils), UsernamePasswordAuthenticationFilter().javaClass)
//            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .formLogin().disable()
//        return http.build()
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests()
            //.antMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
            .antMatchers(HttpMethod.POST,"/login").permitAll()
            .anyRequest().authenticated()

        http.addFilterBefore(
            JWTLoginFilter(authManager = configuration.authenticationManager, jwtUtils = jwtUtils),
            UsernamePasswordAuthenticationFilter::class.java
        )

        http.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtils), OncePerRequestFilter::class.java)

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}