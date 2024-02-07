package tacos.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.controllers.OrderController;

@Slf4j
@RestController
public class IngredientController {

	RestTemplate rest;
	
	public IngredientController(RestTemplate rest) {
		this.rest=rest;
	}
	
	public Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
	}
	
	public Ingredient getIngredientByIdEntity(String ingredientId) {
		ResponseEntity<Ingredient> responseEntity= rest.getForEntity("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
		log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
		return responseEntity.getBody();
	}
	
	public void updateIngredient(Ingredient ingredient) {
		rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
	}
	
	public Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject("http://localhost:8080/ingredients/{id}", ingredient, Ingredient.class);
	}
}
