package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.User;
import tacos.data.UserRepository;

@Configuration
public class SecurityConfig{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user= userRepo.findByUsername(username);
			if(user!=null) return user;
			
			throw new UsernameNotFoundException("User ‘" + username + "’ not found");
		};
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true))
                .oauth2Login(login -> login
                        .loginPage("/login"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .build();
	}
	
}
