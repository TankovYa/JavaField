/*package tacos.authorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.User;
import tacos.data.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		return http
                .authorizeRequests(x ->
                        x.anyRequest().authenticated()
                )
                .formLogin()
                .and().build();
	}
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> userRepo.findByUsername(username);
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	 }
	
	@Bean
	 ApplicationRunner dataLoader(UserRepository repo, PasswordEncoder encoder) {
	 return args -> {
		 repo.save(new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
		 repo.save(new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
	 };
	}
}*/
