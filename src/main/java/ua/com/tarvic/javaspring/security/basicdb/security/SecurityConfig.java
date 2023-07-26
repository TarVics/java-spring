package ua.com.tarvic.javaspring.security.basicdb.security;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import ua.com.tarvic.javaspring.security.basicdb.models.Customer;
import ua.com.tarvic.javaspring.security.basicdb.services.CustomerService;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private CustomerService customerService;

    // user in memmory -> db

    @Bean
    public UserDetailsService userDetailsService() {

//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                Customer customer = customerService.findByUsername(username);
//                return new User(
//                        customer.getUsername(),
//                        customer.getPassword(),
//                        List.of(new SimpleGrantedAuthority(customer.getRole()))
//                        // Arrays.asList(new SimpleGrantedAuthority(customer.getRole()))
//                );
//            }
//        }

        return username -> {
            Customer customer = customerService.findByUsername(username);
            return new User(
                customer.getUsername(),
                customer.getPassword(),
                List.of(new SimpleGrantedAuthority(customer.getRole()))
                // Arrays.asList(new SimpleGrantedAuthority(customer.getRole()))
            );
        };
    }

    // filter chain url

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {

        httpSecurity.authorizeHttpRequests(auth ->
            auth
                .requestMatchers(HttpMethod.GET, "/admin").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/user").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/save").permitAll()
                .anyRequest().permitAll()
        )
        .httpBasic(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}