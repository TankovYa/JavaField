package tacos.web.api;

import java.util.Date;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

	private TacoRepository tacoRepo;
	
	public TacoController(TacoRepository tacoRepo) {
		 this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(params="recent")
	public Iterable<Taco> recentTacos(){
		Pageable page= PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page);
 	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
		Optional<Taco> optTaco= tacoRepo.findById(id);
		if(optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
	
	@PutMapping(path = "/{tacoId}", consumes = "application/json")
	public Taco putTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco taco) {
		taco.setId(tacoId);
		return tacoRepo.save(taco);
	}
	
	@PatchMapping(path = "/{tacoId}", consumes = "application/json")
	public Taco patchTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco patch) {
		Taco taco= tacoRepo.findById(tacoId).get();
		if(patch.getName() != null) {
			taco.setName(patch.getName());
		}
		if(patch.getIngredients() != null ) {
			taco.setIngredients(patch.getIngredients());
		}
		taco.setCreatedAt(new Date());
		return tacoRepo.save(taco);
	}
	
	@DeleteMapping(path = "/{tacoId}")
	public ResponseEntity<Taco> deleteTaco(@PathVariable("tacoId") Long tacoId) {
		try {
			tacoRepo.deleteById(tacoId);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
