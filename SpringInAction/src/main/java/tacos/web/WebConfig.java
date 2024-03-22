package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;
import tacos.security.RegistrationForm;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@Profile("!prod")
	public CommandLineRunner dataLoader(IngredientRepository ingRepo,TacoRepository tacoRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
		return args ->{
			ingRepo.saveAll(
					Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
							new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
							new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
							new Ingredient("CARN", "Carnitas", Type.PROTEIN),
							new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
							new Ingredient("LETC", "Lettuce", Type.VEGGIES),
							new Ingredient("CHED", "Cheddar", Type.CHEESE),
							new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
							new Ingredient("SLSA", "Salsa", Type.SAUCE),
							new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
					));
			
			Taco taco1= new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(ingRepo.findAllById(Arrays.asList("FLTO","GRBF","CARN","SRCR","SLSA","CHED")));
			tacoRepo.save(taco1);
			Taco taco2= new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(ingRepo.findAllById(Arrays.asList("COTO","GRBF","CHED","JACK","SLSA")));
			tacoRepo.save(taco2);
			Taco taco3= new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(ingRepo.findAllById(Arrays.asList("FLTO","COTO","TMTO","LETC","SLSA")));
			tacoRepo.save(taco3);
			
			userRepo.save(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
			userRepo.save(new User("tacochef", passwordEncoder.encode("password"), "ROLE_ADMIN"));
		};
	}
}
