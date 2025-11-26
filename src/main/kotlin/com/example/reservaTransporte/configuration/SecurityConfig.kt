package com.example.reservaTransporte.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // ==== AUTORIZAÇÃO ====
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/api/**").authenticated()  // API protegida
                    .anyRequest().permitAll()                     // tudo mais liberado (útil em dev)
            }
            // ==== BASIC AUTH (você já usa admin/admin123) ====
            .httpBasic(withDefaults())

            // ==== CSRF (desativado só em dev - ok para seu caso) ====
            .csrf { it.disable() }

            // ==== CORS (essencial para frontend separado no 5500) ====
            .cors { it.configurationSource(corsConfigurationSource()) }

            // ==== CSP (libera connect-src para localhost:8080 e Live Server) ====
            .headers { headers ->
                headers.contentSecurityPolicy {
                    it.policyDirectives(
                        "default-src 'self'; " +
                                "connect-src 'self' http://localhost:8080 http://127.0.0.1:5500 http://localhost:5500; " +
                                "script-src 'self' 'unsafe-inline'; " +
                                "style-src 'self' 'unsafe-inline'; " +
                                "img-src 'self' data:;"
                    )
                }
            }

        return http.build()
    }

    // ==== CONFIGURAÇÃO DE CORS (permite o Live Server acessar a API) ====
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf(
                "http://127.0.0.1:5500",
                "http://localhost:5500"
            )
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}